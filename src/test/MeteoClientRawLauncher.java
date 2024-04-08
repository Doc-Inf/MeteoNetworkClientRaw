package test;

import controller.MeteoClientRaw;

public class MeteoClientRawLauncher {
	public static void main(String[] args) {
		new Thread(new MeteoClientRaw("api.meteonetwork.it",443)).start();
	}
}
