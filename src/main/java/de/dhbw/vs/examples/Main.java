package de.dhbw.vs.examples;

import de.dhbw.vs.Client;
import de.dhbw.vs.Server;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.dhbw.vs.utils.logging.LogLevel;
import de.dhbw.vs.utils.logging.Logging;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.Inet4Address;


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

		try {
			Client c = new Client(12345);
			Server s = new Server(11111);
			String str = "Test";
			byte[] t = str.getBytes();
			c.send(new DatagramPacket(t, 4, c.getInet(), 11111));
			s.receive();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
