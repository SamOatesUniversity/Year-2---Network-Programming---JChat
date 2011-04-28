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
 * @author Sam
 */
public class JChatUDPRecieve extends Thread {

    private DatagramSocket      s;
    private DatagramPacket      p;
    private static int          BUFFER_MAX = 100;
    private boolean             running;
    private JChatForm           form;


    public JChatUDPRecieve(DatagramSocket socket, JChatForm form)
    {
        this.s = socket;
        this.running = true;
        this.form = form;
    }

    @Override
    public void run()
    {
        while( running )
        {
            RecieveServerMessage();


        }
    }

    private void RecieveServerMessage()
    {
        try {
            p = new DatagramPacket(new byte[BUFFER_MAX], BUFFER_MAX);

            s.setSoTimeout(1000); // exit after 1 seconds
            try {

                s.receive(p); //blocking

                String message = new String(p.getData()).trim();
                Point avatar_position = new Point();
                System.out.println("Recieved : " + message );

                //if avatar for ip doesnt exist, make new avatar.
                //else update previous avatar location.

                try {
                    int comma = message.indexOf(',');
                    if( comma >= 0 )
                    {
                        avatar_position.x = Integer.parseInt(message.substring(0, comma));
                        avatar_position.y = Integer.parseInt(message.substring(comma + 1));
                    }

                } catch( NumberFormatException e ) {

                }

                form.addAvatar(avatar_position);

            } catch (SocketTimeoutException e) {

                System.out.println("\nTIME OUT EXPIRED");
                //running = false;
            }

        } catch (IOException e) {
            e.printStackTrace();
            running = false;
        }
    }
}
