/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jchat;

import java.awt.Point;
import java.io.IOException;
import java.net.*;

/**
 *
 * @author j9060283
 */
public class JChatUDP extends Thread {

    private int                 port;
    private InetAddress         ip;
    private DatagramSocket      s;
    private DatagramPacket      p;
    private static int          BUFFER_MAX = 100;
    private boolean             running;
    private Point               avatar;
    private int                 avatarid;
    private JChatForm           form;

    public JChatUDP( DatagramSocket socket, JChatForm form ) {
        s = socket;
        running = true;
        this.form = form;
        avatar = new Point( 0, 0 );
        avatarid = form.addAvatar(avatar);
    }

    @Override
    public void run() {

        while( running ) {
            avatar = form.getAvatar( avatarid );
            SendServerMessage();
        }
    }

    public void SendServerMessage() {
        try {

            String message = avatar.x + "," + avatar.y;

            byte[] mb = message.getBytes();

            p = new DatagramPacket(mb, mb.length, ip, port);

            s.send(p);

            System.out.println("Udp Packeet Sent : " + message );

        } catch (SocketException e) {
            System.out.println("COMMUNICATION WITH SERVER LOST --> " + e);
            e.printStackTrace();

        } catch (IOException e) {
            System.out.println("ReadServerMessage" + e);
            e.printStackTrace();
        }
    }

}
