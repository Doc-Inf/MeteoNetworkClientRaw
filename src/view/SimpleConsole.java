package view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SimpleConsole {
	
	private static BufferedReader in;
	
	static {
		in = new BufferedReader(new InputStreamReader(System.in));
	}
	
	public static void log(Object message) {
		System.out.println(message);
	}
	
	public static String read(Object message) {
		System.out.println(message);
		String result = null;
		try {
			result = in.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public static int readInt(Object message) {
		System.out.println(message);
		Integer result = null;
		try {
			result = Integer.parseInt(in.readLine());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public static double readDouble(Object message) {
		System.out.println(message);
		Double result = null;
		try {
			result = Double.parseDouble(in.readLine());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
