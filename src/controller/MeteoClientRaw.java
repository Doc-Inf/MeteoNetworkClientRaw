package controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

public class MeteoClientRaw implements Runnable{

	private String hostname;
	private int port;
	//private Object lock;
	private SSLSocket socket;
	private PrintWriter out;
	private BufferedReader in;
	
	
	public MeteoClientRaw(String hostname, int port) {
		this.hostname = hostname;
		this.port = port;
	}
	
	@Override
	public void run() {
		try {
			SSLSocketFactory sslsocketfactory = (SSLSocketFactory) SSLSocketFactory.getDefault();
			socket = (SSLSocket) sslsocketfactory.createSocket(hostname, port);
			in = new BufferedReader( new InputStreamReader( socket.getInputStream() ) );
			out = new PrintWriter( socket.getOutputStream() );
			new Thread(new MeteoClientRawListener(in)).start();			
			login();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private void login() {
		
		String requestBody = "--ed5636f3-fc21-4b6a-8404-f41d144329d7\r\n" +
							 "Content-Disposition: form-data; name=\"email\"\r\n" +
							 "Content-Type: text/plain; charset=utf-8\r\n" +
							 "\r\n" +
							 "andre.bonifazi@gmail.com\r\n" +
							 "--ed5636f3-fc21-4b6a-8404-f41d144329d7\r\n" +
							 "Content-Disposition: form-data; name=\"password\"\r\n" +
							 "Content-Type: text/plain; charset=utf-8\r\n" +
							 "\r\n" +
							 "V4ll4ur1\r\n" +
							 "--ed5636f3-fc21-4b6a-8404-f41d144329d7--\r\n";
		
		String request = "POST /v3/login HTTP/1.1\r\n" +
						 "Content-Type: multipart/form-data; boundary=ed5636f3-fc21-4b6a-8404-f41d144329d7\r\n" +
						 "Cache-Control: no-cache\r\n" +
						 "Pragma: no-cache\r\n" +
						 "User-Agent: Java/22-ea\r\n" +
						 "Host: api.meteonetwork.it:443\r\n" +
						 "Accept: */*\r\n" +
						 "Connection: keep-alive\r\n" +
						 "Content-Length: 339\r\n" +								
						 "\r\n" +
						 "--ed5636f3-fc21-4b6a-8404-f41d144329d7\r\n" +
						 "Content-Disposition: form-data; name=\"email\"\r\n" +
						 "Content-Type: text/plain; charset=utf-8\r\n" +
						 "\r\n" +
						 "andre.bonifazi@gmail.com\r\n" +
						 "--ed5636f3-fc21-4b6a-8404-f41d144329d7\r\n" +
						 "Content-Disposition: form-data; name=\"password\"\r\n" +
						 "Content-Type: text/plain; charset=utf-8\r\n" +
						 "\r\n" +
						 "V4ll4ur1\r\n" +
						 "--ed5636f3-fc21-4b6a-8404-f41d144329d7--\r\n";
		
		System.out.println("Request body length: " + requestBody.getBytes().length);
		System.out.println("Request:\n" + request);
				
		out.println(request);
		out.flush();
		
	}

}
