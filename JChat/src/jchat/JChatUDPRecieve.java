/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jchat;

import java.awt.Point;
import java.io.IOException;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Sam
 */
public class JChatUDPRecieve extends Thread {

    private class JChatUDPClient
    {
        public int         id;
        public int         avatar_id;

        public JChatUDPClient( int id, int avatar_id )
        {
            this.id = id;
            this.avatar_id = avatar_id;
        }
    }

    private DatagramSocket          s;
    private DatagramPacket          p;
    private static int              BUFFER_MAX = 100;
    private boolean                 running;
    private JChatForm               form;

    private ArrayList<JChatUDPClient>    client;

    public JChatUDPRecieve(DatagramSocket socket, JChatForm form)
    {
        this.s = socket;
        this.running = true;
        this.form = form;
        client = new ArrayList<JChatUDPClient>();
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
                int id = 0;
                //System.out.println("Recieved : " + message );

                //if avatar for ip doesnt exist, make new avatar.
                //else update previous avatar location.

                try {
                    int comma = message.indexOf(',');
                    String position = "";
                    if( comma >= 0 )
                    {
                        id = Integer.parseInt(message.substring(0, comma));
                        position = message.substring(comma + 1);
                    }
                    //System.out.println( position );
                    comma = position.indexOf(',');
                    if( comma >= 0 )
                    {
                        avatar_position.x = Integer.parseInt(position.substring(0, comma));
                        avatar_position.y = Integer.parseInt(position.substring(comma + 1));
                    }

                } catch( NumberFormatException e ) {

                }

                //System.out.println("ID : " + id + " - Position : " + avatar_position.toString() );

                if( avatar_position.x > 0 && avatar_position.y > 0 )
                {
                    boolean new_client = true;
                    for( int i = 0; i < client.size(); i++ )
                    {
                        if( client.get(i).id == id )
                        {
                            new_client = false;
                            form.updateAvatar(client.get(i).avatar_id, avatar_position);
                        }
                    }

                    if( new_client && id <= client.size() )
                    {
                        int avatar_id = form.addAvatar(avatar_position);
                        JChatUDPClient new_udpclient = new JChatUDPClient(id, avatar_id);
                        client.add(new_udpclient);
                    }
                }

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
