package de.dhbw.vs.enclosingclass;

import de.dhbw.vs.client.ClientTCP;

import java.io.IOException;
import java.net.InetAddress;
import java.util.concurrent.TimeUnit;

public class EnclosingClassClientTCP implements Runnable {
    private int number;
    private String inet;
    public EnclosingClassClientTCP (int number, String inet) {
        this.number = number;
        this.inet = inet;
    }

    @Override
    public void run() {
        ClientTCP clientTCP;
        try {
            clientTCP = new ClientTCP(11111, InetAddress.getByName(this.inet));
            String str;

            for (int j = 0; j < 20; j++) {
                str = " Hallo Welt # " + j + " @" + new java.text.SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZZZZZ").format(new java.util.Date()) + " Client Nr.: " + this.number;
                clientTCP.send(str);
                TimeUnit.MILLISECONDS.sleep(500);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
