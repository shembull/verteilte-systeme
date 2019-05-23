package de.dhbw.vs;

import java.io.IOException;
import java.net.*;
import java.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.dhbw.vs.utils.logging.LogLevel;
import de.dhbw.vs.utils.logging.Logging;

public class Server {

    private DatagramSocket socket;
    private DatagramPacket packet;
    private Logger log;

    static {
        Logging.setLoggingDefaults(LogLevel.DEBUG, "[%-5p; %c{1}::%M] %m%n");
    }

    public Server(int port) throws SocketException, UnknownHostException {
        log = LoggerFactory.getLogger(Server.class);
        socket = new DatagramSocket(port, Inet4Address.getLocalHost());
        byte[] buf = new byte[1000];
        packet = new DatagramPacket(buf, 1000);
    }
    public void receive() throws IOException {

        socket.receive(packet);
        log.info(new String(packet.getData()));
        packet.setData(new byte[1000]);

    }
}
