package de.dhbw.vs.client;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.dhbw.vs.utils.logging.LogLevel;
import de.dhbw.vs.utils.logging.Logging;

public class ClientTCP {

    private Socket socket;
    private Logger log;
    private int port;
    private InetAddress inetAddress;


    static {
        Logging.setLoggingDefaults(LogLevel.DEBUG, "[%-5p; %c{1}::%M] %m%n");
    }

    public ClientTCP(int port, InetAddress inetAddress) throws IOException {
        this.log = LoggerFactory.getLogger(ClientTCP.class);
        this.port = port;
        this.inetAddress = inetAddress;
        this.socket = new Socket(this.inetAddress, this.port);
    }

    public void send(String data) throws IOException {
        if (this.socket.isClosed()){
            this.socket = new Socket(this.inetAddress, this.port);
        }
        //log.debug("Creating writer");
        BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(
                        this.socket.getOutputStream()
                )
        );
        //log.debug("Writer created");
        //log.debug("Writing data...");
        writer.write(data);
        //log.debug("Data written");
        //log.debug("Flushing writer...");
        writer.flush();
        //log.debug("Writer flushed");
        this.socket.shutdownOutput();

        BufferedReader reader = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
        //log.debug("Start reading data");
        while (reader.read() > -1){
            //log.debug(Integer.toString(reader.read()));
            log.info(reader.readLine());
        }
        this.socket.close();
    }

}
