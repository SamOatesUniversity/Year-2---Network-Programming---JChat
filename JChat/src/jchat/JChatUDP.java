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
 
 /**
*The UDP Thread
*
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

    private JChatUDPRecieve     recieve_loop;

	/**
	*The constructor for the UDP thread of the client 
	@param socket The socket to be used for UDP connection
	*
	@param form The GUI of the client
	*
	@return none.
	*/
	
    public JChatUDP( DatagramSocket socket, JChatForm form ) {
        s = socket;
        running = true;
        this.form = form;
        avatar = new Point( 0, 0 );
        avatarid = form.addAvatar(avatar);

        recieve_loop = new JChatUDPRecieve(s, form);
        recieve_loop.start();
    }

	/**
	*Main run point of the thread, sends avatars position to the server
	*
	@return none.
	*/
	
    @Override
    public void run() {

        while( running ) {
            avatar = form.getAvatar( avatarid );
            SendServerMessage();
        }
    }

	/**
	*Sends the avatars position to the server in the format x,y
	*
	@return none.
	*/
	
    public void SendServerMessage() {
        try {

            String message = avatar.x + "," + avatar.y;

            byte[] mb = message.getBytes();

            p = new DatagramPacket(mb, mb.length, ip, port);

            s.send(p);

        } catch (SocketException e) {
            System.out.println("COMMUNICATION WITH SERVER LOST --> " + e);
            e.printStackTrace();

        } catch (IOException e) {
            System.out.println("ReadServerMessage" + e);
            e.printStackTrace();
        }
    }

}
