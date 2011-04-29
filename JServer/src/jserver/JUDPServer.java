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
        public int              time;
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
            has_new_message = false;
        } catch (IOException ex) {
            Logger.getLogger(JUDPServer.class.getName()).log(Level.SEVERE, null, ex);
            has_new_message = false;
        }

        if( has_new_message ) {
            String message = new String(packet.getData()).trim();
            boolean is_new_client = true;
            InetAddress ip = packet.getAddress();
            int port = packet.getPort();
            int from = -1;
            String ip_string = ip.getHostAddress();

            for( int i = 0; i < client.size(); i++ ) {
                client.get(i).time = client.get(i).time + 1;
                if( ip_string.equalsIgnoreCase(client.get(i).ip) &&
                    client.get(i).port == port )
                {
                    client.get(i).latest_message = message;
                    client.get(i).time = 0;
                    is_new_client = false;
                    from = i;
                }

                if( client.get(i).time > 100000 ) {
                    //no udp message in 100,000 cycles...
                    //presume dead...
                    client.get(i).latest_message = "remove," + i;
                }
            }

            if( is_new_client ) {
                JUDPClient new_client = new JUDPClient();
                new_client.ip = ip_string;
                new_client.port = port;
                new_client.time = 0;
                client.add(new_client);
                from = client.size() - 1;
            }

            SendMessageToOthers( from );

            for( int i = 0; i < client.size(); i++ )
            {
                if( client.get(i).time > 40000 ) {
                    //no udp message in 40000 cycles...
                    client.get(i).latest_message = "remove";
                    SendMessageToOthers( i );
                }
            }
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
                        client.remove(sender);
                    }
                } catch( NullPointerException ex ) {
                    
                }

            }
        }
    }
}
