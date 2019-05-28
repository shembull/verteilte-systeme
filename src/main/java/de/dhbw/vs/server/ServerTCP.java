package de.dhbw.vs.server;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.dhbw.vs.utils.logging.LogLevel;
import de.dhbw.vs.utils.logging.Logging;

public class ServerTCP {

    private ServerSocket serverSocket;
    private Socket socket;
    private InputStream inputStream;
    private Logger log;


    static {
        Logging.setLoggingDefaults(LogLevel.DEBUG, "[%-5p; %c{1}::%M] %m%n");
    }

    public ServerTCP(int port, int backlog, InetAddress inetAddress) throws IOException {
        log = LoggerFactory.getLogger(ServerTCP.class);
        serverSocket = new ServerSocket(port, backlog, inetAddress);
        this.exceptNewClient();
    }

    private void exceptNewClient() {
        new Thread(() -> {
            try {
                this.socket = serverSocket.accept();
                if (!this.checkConState()) {
                    log.warn("Socket is not connected");
                }
                else {
                    //log.debug("Socket is connected");
                    this.inputStream = this.socket.getInputStream();
                    this.read();
                    this.socket.close();
                    this.socket = null;
                    this.exceptNewClient();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    private void read() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        //log.debug("Start reading data");
        String text = "";
        while (reader.read() > -1){
            //log.debug(Integer.toString(reader.read()));
            text = reader.readLine();
            log.info(text);
        }
        //log.debug("Finished reading data");
        BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(
                        this.socket.getOutputStream()
                )
        );

        writer.write(" Anfrage verarbeitet Client Nr.: " + text.substring(text.length() - 1));
        writer.flush();
    }

    private boolean checkConState(){
        return this.socket.isConnected();
    }
}
