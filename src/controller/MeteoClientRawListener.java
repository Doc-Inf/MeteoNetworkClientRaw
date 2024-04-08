package controller;

import java.io.BufferedReader;
import java.io.IOException;

import static view.SimpleConsole.*;

public class MeteoClientRawListener implements Runnable{

	private BufferedReader in;
	
	public MeteoClientRawListener(BufferedReader in) {
		this.in = in;
	}
	
	
	@Override
	public void run() {
		log("Listener running...");
		String line = null;
		do {
			try {
				line = in.readLine();
				if(line != null) {
					System.out.println("SERVER: " + line);
				}
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}while(true);
	}

}
