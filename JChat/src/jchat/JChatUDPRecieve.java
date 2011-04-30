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
 * @author j9060283
 */
 
 /**
*The UDP Receive Loop Thread
*
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

	/**
	*Constructor for the UDP receive thread
	@param socket The socket to receive messages from
	*
	@param form The GUI of the client
	*
	@return none.
	*/
	
    public JChatUDPRecieve(DatagramSocket socket, JChatForm form)
    {
        this.s = socket;
        this.running = true;
        this.form = form;
        client = new ArrayList<JChatUDPClient>();
    }

	/**
	*Main run point of the thread, receives server messages
	*
	@return none.
	*/
	
    @Override
    public void run()
    {
        while( running )
        {
            RecieveServerMessage();
        }
    }

	/**
	*Waits to receive messages from the server, if the message
	*contains the word 'remove' remove the client avatar from the form
	*else update the clients avatar position
	*
	@return none.
	*/
	
    private void RecieveServerMessage()
    {
        try {
            p = new DatagramPacket(new byte[BUFFER_MAX], BUFFER_MAX);

            s.setSoTimeout(1000); // exit after 1 seconds
            try {

                s.receive(p); //blocking

                String message = new String(p.getData()).trim();

                if( !message.contains("remove") )
                {
                    Point avatar_position = new Point(-1, -1);
                    int id = -1;

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

                    if( avatar_position.x >= 0 && avatar_position.y >= 0 && id >= 0 )
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

                        if( new_client )
                        {
                            int avatar_id = form.addAvatar(avatar_position);
                            JChatUDPClient new_udpclient = new JChatUDPClient(id, avatar_id);
                            client.add(new_udpclient);
                        }
                    }
                }
                else
                {
                    //Remove client
                    int id = -1;
                    try {
                        int comma = message.indexOf(',');
                        
                        if( comma >= 0 )
                        {
                            id = Integer.parseInt(message.substring(0, comma));
                        }
                    } catch( NumberFormatException e ) {

                    }
                    
                    for( int i = 0; i < client.size(); i++ )
                    {
                        if( client.get(i).id == id )
                        {
                            form.removeAvatar( client.get(i).avatar_id );
                            client.remove(i);
                        }
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
