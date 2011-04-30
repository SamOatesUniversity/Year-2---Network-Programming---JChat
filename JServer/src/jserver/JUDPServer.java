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

    private ArrayList<JUDPClient>       client;
    private DatagramSocket              socket;
    private DatagramPacket              packet;
    private boolean                     running;
    private JUDPServerReceive           udp_receive;

    public JUDPServer( int port, JServerForm form ) {
        running = true;
        try {
            socket = new DatagramSocket(port);
        } catch (SocketException ex) {
            Logger.getLogger(JUDPServer.class.getName()).log(Level.SEVERE, null, ex);
            running = false;
        }

        client = new ArrayList<JUDPClient>();

        udp_receive = new JUDPServerReceive(socket);
        udp_receive.start();

        System.out.println("## UDP SERVER STARTED ##");
    }

    @Override
    public void run() {

        while( running ) {

            for( int i = 0; i < udp_receive.clientCount(); i++ )
            {
                client.add(udp_receive.getClient(i));
            }

            for( int i = 0; i < client.size(); i++ )
            {
                try {
                    if( client.get(i).has_message ) {
                        SendMessageToOthers( i );
                        client.get(i).has_message = false;
                    }
                } catch( NullPointerException ex ) {
                    
                }
            }

            client.clear();
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

                try {
                    if( client.get(sender).latest_message.contains("remove")) {
                        this.udp_receive.removeClient(sender);
                    }
                } catch( NullPointerException ex ) {
                    
                }

            }
        }
    }
}
