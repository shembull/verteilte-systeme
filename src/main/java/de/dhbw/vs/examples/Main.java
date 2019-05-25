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
import java.lang.reflect.Array;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Arrays;
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
		System.out.println(Arrays.toString(args));
		if(args[0].equals("task2-1")){
			// task 2-1
			//////////////////////////////////////////////////////////////////////////////////////
			if(args[1].equals("client")){
				log.debug("Task2-1, client started...");
				try {
					Client client = new Client(12345);
					String str;
					InetAddress inet = client.getInet();

					for (int i = 0; i < 20; i++) {
						str = "Hallo Welt # " + i + " @" + new java.text.SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZZZZZ").format(new java.util.Date());
						//str = "djfnriena.qlwuebdhstarwtsbdkepwirtzstarcbemböptisqmdjfnriena.qlwuebdhstarwtsbdkepwirtzstarcbemböptisqmdjfnriena.qlwuebdhstarwtsbdkepwirtzstarcbemböptisqmdjfnriena.qlwuebdhstarwtsbdkepwirtzstarcbemböptisqm";
						byte[] b = str.getBytes();
						client.send(new DatagramPacket(b, b.length, inet, 11111));
						TimeUnit.MILLISECONDS.sleep(500);
					}
				} catch (InterruptedException | IOException e) {
					e.printStackTrace();
				}
			}
			else if(args[1].equals("server")){
				log.debug("Task2-1, server started...");
				try {
					Server server = new Server(11111);
					while (true) {
						server.receive();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			///////////////////////////////////////////////////////////////////////////////////////////
		}

		else if(args[0].equals("task2-2")) {
			// task 2-2
			//////////////////////////////////////////////////////////////////////////////////////////
			if(args[1].equals("client")){
				log.debug("Task2-2, client started...");
				ClientTCP clientTCP;
				try {
					clientTCP = new ClientTCP(11111);
					String str;

					for (int i = 0; i < 20; i++) {
						str = " Hallo Welt # " + i + " @" + new java.text.SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZZZZZ").format(new java.util.Date()) + "\n";
						clientTCP.send(str);
						TimeUnit.MILLISECONDS.sleep(500);
					}
				} catch (IOException | InterruptedException e) {
					e.printStackTrace();
				}

			}
			else if(args[1].equals("server")) {
				log.debug("Task2-2, server started...");
				try {
					new ServerTCP(11111, 500);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
