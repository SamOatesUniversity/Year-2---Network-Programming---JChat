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
public class JUDPServer extends Thread {

    class JUDPClient {
        public String           ip;
        public int              port;
        public String           latest_message;
    }

    private ArrayList<JUDPClient>       client;
    private DatagramSocket              socket;
    private DatagramPacket              packet;
    private boolean                     running;

    public JUDPServer( int port, JServerForm form ) {
        running = true;
        try {
            socket = new DatagramSocket(port);
        } catch (SocketException ex) {
            Logger.getLogger(JUDPServer.class.getName()).log(Level.SEVERE, null, ex);
            running = false;
        }

        client = new ArrayList<JUDPClient>();

        System.out.println("## UDP SERVER STARTED ##");
    }

    @Override
    public void run() {

        while( running ) {
            //Recieve UDP Message
            recieveMessage();
        }

    }

    public void recieveMessage() {

        boolean has_new_message = true;
        byte[] buf = new byte[256];
        packet = new DatagramPacket(buf, buf.length);
        try {
            socket.receive(packet);
        } catch ( SocketException ex ) {
            
        } catch (IOException ex) {
            Logger.getLogger(JUDPServer.class.getName()).log(Level.SEVERE, null, ex);
        }

        if( has_new_message ) {
            boolean is_new_client = true;
            String message = new String(packet.getData()).trim();
            InetAddress ip = packet.getAddress();
            int port = packet.getPort();
            int from = -1;
            String ip_string = ip.getHostAddress();

            for( int i = 0; i < client.size(); i++ ) {
                if( ip_string.equalsIgnoreCase(client.get(i).ip) &&
                    client.get(i).port == port )
                {
                    client.get(i).latest_message = message;
                    is_new_client = false;
                    from = i;

                    //System.out.println("## Exisiting Client : " + message + " ##");
                }
            }

            if( is_new_client ) {
                JUDPClient new_client = new JUDPClient();
                new_client.ip = ip_string;
                new_client.port = port;
                client.add(new_client);
                from = client.size() - 1;
                //System.out.println("## New Client : " + message + " ##");
            }

            SendMessageToOthers( from );
        }
    }

    private void SendMessageToOthers( int sender ) {
        for( int i = 0; i < client.size(); i++ ) {
            if( client.get(i) != client.get(sender) ) {

                try {

                    String message = sender + "," + client.get(sender).latest_message;
                    byte[] buf = message.getBytes();

                    try {
                        packet = new DatagramPacket(buf, buf.length, InetAddress.getByName(client.get(i).ip), client.get(i).port);
                    } catch (UnknownHostException ex) {
                        Logger.getLogger(JUDPServer.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    try {
                        socket.send(packet);
                    } catch (IOException ex) {
                        Logger.getLogger(JUDPServer.class.getName()).log(Level.SEVERE, null, ex);
                    }

                } catch (NullPointerException ex) {
                    
                }

            }
        }
    }
}
