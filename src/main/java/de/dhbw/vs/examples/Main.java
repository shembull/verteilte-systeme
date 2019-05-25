package de.dhbw.vs.examples;

import de.dhbw.vs.client.Client;
import de.dhbw.vs.client.ClientTCP;
import de.dhbw.vs.server.Server;
import de.dhbw.vs.server.ServerTCP;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.dhbw.vs.utils.logging.LogLevel;
import de.dhbw.vs.utils.logging.Logging;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.util.concurrent.TimeUnit;


public class Main {

	static {
		Logging.setLoggingDefaults(LogLevel.DEBUG, "[%-5p; %c{1}::%M] %m%n");
	}

	public static void main(String[] args) {
		// Obtain an instance of a logger for this class
		Logger log = LoggerFactory.getLogger(Main.class);

		// Output a simple log statement
		/*log.info("Hallo Welt!");
		System.out.println("Hallo Welt!".getBytes());*/

		// task 2-1
		//////////////////////////////////////////////////////////////////////////////////////
		/*
		try {
			Client c = new Client(12345);
			Server s = new Server(11111);
			String str;
			InetAddress inet = c.getInet();

			new Thread(() -> {
				while (true){
					try {
						s.receive();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}).start();

			for (int i = 0; i < 20; i++) {
				str = "Hallo Welt # " + i + " @" + new java.text.SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZZZZZ").format(new java.util.Date());
				//str = "djfnriena.qlwuebdhstarwtsbdkepwirtzstarcbemböptisqmdjfnriena.qlwuebdhstarwtsbdkepwirtzstarcbemböptisqmdjfnriena.qlwuebdhstarwtsbdkepwirtzstarcbemböptisqmdjfnriena.qlwuebdhstarwtsbdkepwirtzstarcbemböptisqm";
				byte[] b = str.getBytes();
				c.send(new DatagramPacket(b, b.length, inet, 11111));
				TimeUnit.MILLISECONDS.sleep(500);
			}

		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
		*/
		///////////////////////////////////////////////////////////////////////////////////////////

		// task 2-2
		//////////////////////////////////////////////////////////////////////////////////////////
		ClientTCP clientTCP;
		try {
			new ServerTCP(11111, 1000);
			clientTCP = new ClientTCP(11111);
			String str;

			for (int i = 0; i < 20; i++) {
				str = "Hallo Welt # " + i + " @" + new java.text.SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZZZZZ").format(new java.util.Date()) + "\n";
				//str = "djfnriena.qlwuebdhstarwtsbdkepwirtzstarcbemböptisqmdjfnriena.qlwuebdhstarwtsbdkepwirtzstarcbemböptisqmdjfnriena.qlwuebdhstarwtsbdkepwirtzstarcbemböptisqmdjfnriena.qlwuebdhstarwtsbdkepwirtzstarcbemböptisqm";
				clientTCP.send(str);
				TimeUnit.MILLISECONDS.sleep(500);
			}
			//log.info(Boolean.toString(serverTCP.checkConState()));
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
	}

}
