package de.dhbw.vs;

import java.io.IOException;
import java.net.*;

public class Client {

    private DatagramSocket socket;
    public Client(int port) throws SocketException, UnknownHostException {
        socket = new DatagramSocket(port, Inet4Address.getLocalHost());
    }

    public void send(DatagramPacket p) throws IOException {
        socket.send(p);
    }

    public InetAddress getInet(){
        return socket.getLocalAddress();
    }

}
