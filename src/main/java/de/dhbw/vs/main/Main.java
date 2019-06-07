package de.dhbw.vs.main;

import de.dhbw.vs.client.Client;
import de.dhbw.vs.enclosingclass.EnclosingClassClientTCP;
import de.dhbw.vs.server.Server;
import de.dhbw.vs.server.ServerTCP;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.dhbw.vs.utils.logging.LogLevel;
import de.dhbw.vs.utils.logging.Logging;

import java.io.IOException;
import java.net.*;
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
		log.debug(Arrays.toString(args));
		if(args[0].equals("task2-1")){
			// task 2-1
			//////////////////////////////////////////////////////////////////////////////////////
			if(args[1].equals("client")){
				log.debug("Task2-1, client started...");
				try {
					Client client = new Client(12345, InetAddress.getByName(args[2]));
					String str;
					InetAddress inet = InetAddress.getByName(args[2]);

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
					Server server = new Server(11111, InetAddress.getByName(args[2]));
                    //noinspection InfiniteLoopStatement
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
				for (int i = 0; i < Integer.parseInt(args[3]); i++){
					EnclosingClassClientTCP ecc = new EnclosingClassClientTCP(i, args[2]);
					Thread thread = new Thread(ecc);
					thread.start();
				}

			}
			else if(args[1].equals("server")) {
				log.debug("Task2-2, server started...");
				try {
					new ServerTCP(11111, 500, InetAddress.getByName(args[2]));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
