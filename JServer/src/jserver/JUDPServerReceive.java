/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jserver;

import java.io.IOException;
import java.net.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Sam
 */
public class JUDPServerReceive extends Thread {

    private boolean running;
    private ArrayList<JUDPClient> client;
    private DatagramSocket socket;
    private DatagramPacket packet;

    public JUDPServerReceive(DatagramSocket socket) {
        running = true;
        this.client = new ArrayList<JUDPClient>();
        this.socket = socket;
    }

    @Override
    public void run() {

        while (running) {
            recieveMessage();
        }

    }

    public int clientCount() {
        return client.size();
    }

    public JUDPClient getClient(int id) {
        return client.get(id);
    }

    public void removeClient( int id ) {
        client.remove(id);
    }

    public void recieveMessage() {

        boolean has_new_message = true;
        byte[] buf = new byte[256];
        packet = new DatagramPacket(buf, buf.length);
        try {
            socket.receive(packet);
        } catch (SocketException ex) {
            has_new_message = false;
        } catch (IOException ex) {
            Logger.getLogger(JUDPServer.class.getName()).log(Level.SEVERE, null, ex);
            has_new_message = false;
        }

        if (has_new_message) {
            String message = new String(packet.getData()).trim();
            boolean is_new_client = true;
            InetAddress ip = packet.getAddress();
            int port = packet.getPort();
            String ip_string = ip.getHostAddress();

            for (int i = 0; i < client.size(); i++) {
                client.get(i).time = client.get(i).time + 1;
                if (ip_string.equalsIgnoreCase(client.get(i).ip)
                        && client.get(i).port == port) {
                    client.get(i).latest_message = message;
                    client.get(i).time = 0;
                    client.get(i).has_message = true;
                    is_new_client = false;
                }

                if (client.get(i).time > 100000) {
                    //no udp message in 100,000 cycles...
                    //presume dead...
                    client.get(i).latest_message = "remove";
                    client.get(i).has_message = true;
                }
            }

            if (is_new_client) {
                JUDPClient new_client = new JUDPClient();
                new_client.ip = ip_string;
                new_client.port = port;
                new_client.time = 0;
                new_client.has_message = true;
                client.add(new_client);
            }
        }
    }
}
