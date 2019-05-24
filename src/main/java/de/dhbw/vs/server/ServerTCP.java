package de.dhbw.vs.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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

    public ServerTCP(int port, int backlog) throws IOException {
        serverSocket = new ServerSocket(port, backlog);
        socket = serverSocket.accept();
        inputStream = socket.getInputStream();
    }

    public void read() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        while (inputStream.read() != -1){
            log.info(reader.readLine());
        }
    }


}
