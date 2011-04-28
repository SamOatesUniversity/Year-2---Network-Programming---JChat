/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jserver;

import java.awt.Point;
import java.io.IOException;
import java.net.*;

/**
 *
 * @author j9060283
 */
public class JUDPLoop extends Thread {

    private boolean             running;
    private DatagramSocket      udpsocket;
    private DatagramPacket      udppacket;
    private static int          BUFFER_MAX = 100;
    private Point               avatar_position;
    private int                 port;
    private InetAddress         ip;

    public JUDPLoop(DatagramSocket udpsocket) {
        this.running = true;
        this.udpsocket = udpsocket;
        this.avatar_position = new Point(0, 0);
        this.port = 0;
        this.ip = null;
    }

    @Override
    public void run() {

        while (running) {
            waitForClientMessage();
            if (running) {
                ip = udppacket.getAddress();
                port = udppacket.getPort();

                Point temp_avatar_position = new Point(-1, -1);

                try {
                    String message = new String(udppacket.getData()).trim();
                    int comma = message.indexOf(',');
                    if( comma >= 0 )
                    {
                        temp_avatar_position.x = Integer.parseInt(message.substring(0, comma));
                        temp_avatar_position.y = Integer.parseInt(message.substring(comma + 1));
                    }

                    if( temp_avatar_position.x >= 0 && temp_avatar_position.y >= 0)
                        avatar_position = temp_avatar_position;

                    //System.out.println("Udp Recieved Message : " + message);
                } catch( NumberFormatException e ) {
                    
                }
            }
        }

        System.out.println("#### UDP Closed ####\n");

    }

    public Point getAvatar() {
        return this.avatar_position;
    }

    public void send(int id, Point pos) {

        if( port != 0 && ip != null ) {

            try {

                String message = id + "," + pos.x + "," + pos.y;

                byte[] mb = message.getBytes();

                udppacket = new DatagramPacket(mb, mb.length, ip, port);

                udpsocket.send(udppacket);

                System.out.println("Server sent UDP packet : " + message);

            } catch ( NullPointerException e ) {
                System.out.println("NULL POINTER --> " + e);
                e.printStackTrace();

            } catch (SocketException e) {
                System.out.println("COMMUNICATION WITH SERVER LOST --> " + e);
                e.printStackTrace();

            } catch (IOException e) {
                System.out.println("ReadServerMessage" + e);
                e.printStackTrace();
            }

        }
         
    }

    public void waitForClientMessage() {

        try {
            udppacket = new DatagramPacket(new byte[BUFFER_MAX], BUFFER_MAX);

            udpsocket.setSoTimeout(10000); // exit after 10 seconds
            try {

                udpsocket.receive(udppacket); //blocking

            } catch (SocketTimeoutException e) {

                System.out.println("\nTIME OUT EXPIRED");
                running = false;
            }

        } catch (IOException e) {
            e.printStackTrace();
            running = false;
        }
    }

    public void kill() {
        running = false;
    }
}
