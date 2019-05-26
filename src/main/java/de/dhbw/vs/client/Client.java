package de.dhbw.vs.client;

import java.io.IOException;
import java.net.*;

public class Client {

    private DatagramSocket socket;
    public Client(int port, InetAddress inetAddress) throws SocketException {
        socket = new DatagramSocket(port, inetAddress);
    }

    public void send(DatagramPacket p) throws IOException {
        socket.send(p);
    }

    public InetAddress getInet(){
        return socket.getLocalAddress();
    }

}
