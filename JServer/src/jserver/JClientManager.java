package jserver;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author J9060283
 */

/**
 * This stores and controls all the TCP clients.
 * and handles sending messages to other clients.
 */
public class JClientManager extends Thread {

    private ArrayList<JClient>  client;
    private boolean             running;
    private JServerForm         form;

    /**
     * Initialise a new client manager.
     * This stores and controls all the TCP clients.
     * and handles sending messages to other clients.
    @param  form  a JServerForm used to add messages to the server GUI.
     *
    @return      none.
     *
     */
    public JClientManager( JServerForm form ) {
        this.client = new ArrayList<JClient>();
        this.running = true;
        this.form = form;
    }

    @Override
    public void run() {

        while( running ) {

            if( client.size() > 0 ) {

                //is dead//
                for( int i = 0; i < client.size(); i++ ) {
                    JClient c = client.get(i);
                    if( !c.isRunning() ) {
                        removeClient( i, c );
                    }
                }

                //server action//
                if( form.isKicked() ) {
                    String cw = form.getKicked();
                    for( int i = 0; i < client.size(); i++ ) {
                        JClient c = client.get(i);
                        if( cw.equals(c.getUsername()) ) {
                            sendMessage( c, "## You have been kicked by the server ##" );
                            removeClient( i, c );
                        } else {
                            sendMessage( c, "## " + cw + " has been kicked by the server ##" );
                        }
                    }
                }

                if( form.isWarning() ) {
                    String cw = form.getWarned();
                    for( int i = 0; i < client.size(); i++ ) {
                        JClient c = client.get(i);
                        if( cw.equals(c.getUsername()) ) {
                            sendMessage( c, "## You have been warned by the server ##" );
                        } else {
                            sendMessage( c, "## " + cw + " has been warned by the server ##" );
                        }
                    }
                }
                
                if( form.isSlaped() ) {
                    String cw = form.getSlaped();
                    for( int i = 0; i < client.size(); i++ ) {
                        JClient c = client.get(i);
                        if( cw.equals(c.getUsername()) ) {
                            sendMessage( c, "## You have been slaped by the server ##" );
                        } else {
                            sendMessage( c, "## " + cw + " has been slaped by the server ##" );
                        }
                    }
                }

                //new message//
                for( int i = 0; i < client.size(); i++ ) {
                    JClient c = client.get(i);
                    if( c.hasMessage() ) {
                        String name = c.getUsername();
                        String message = c.getMessage();
                        form.addMessage(name, message);
                        sendMessageOthers( c, name + " : " + message );
                    }
                }

            }
            
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                Logger.getLogger(JClientManager.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }

    }

    /**
     * sends a message to an individual client.
    @param  c  the JClient the message should be sent too.
     *
    @param  message  a String containing the message to send.
     *
    @return      none.
     *
     */
    public void sendMessage( JClient c, String message ) {
        c.sendMessage(message);
    }

    /**
     * sends a message to every client on the server.
    @param  message  a String containing the message to send.
     *
    @return      none.
     *
     */
    public void sendMessageAll( String message ) {
        for( int i = 0; i < client.size(); i++ ) {
            sendMessage( client.get(i), message );
        }
    }

    /**
     * sends a message to every client except the person
     * who sent the original message on the server.
    @param  sender  the JClient that sent the original message.
     *
    @param  message  a String containing the message to send.
     *
    @return      none.
     *
     */
    public void sendMessageOthers( JClient sender, String message ) {
        for( int i = 0; i < client.size(); i++ ) {
            JClient c = client.get(i);
            if( c != sender ) {
                sendMessage( c, message );
            }
        }
    }

    /**
     * adds a client to the client manager. and sends a message
     * to all other clients stating there connection.
     * Also if a client already in the manager has the same name as
     * the new client, the new client is renamed with the time as a
     * prefix.
    @param  newClient  a new JClient.
     *
    @return      none.
     *
     */
    public void addClient( JClient newClient ) {
        for( int i = 0; i < client.size(); i++ ) {
            JClient c = client.get(i);
            if( c.getUsername().equals(newClient.getUsername()) ) {
                newClient.setUsername( newClient.getUsername() + "_" + System.currentTimeMillis() );
            }
        }
        client.add(newClient);
        form.addClient(newClient.getUsername());
        sendMessageOthers( newClient, "## " + newClient.getUsername() + " has joined the chat ##" );
        System.out.println( "#### Client added to client manager ####");
    }

    /**
     * removes a client from the client loop. and sends a message
     * to all other clients stating which client has left.
    @param  sender  the JClient that sent the original message.
     *
    @param  message  a String containing the message to send.
     *
    @return      none.
     *
     */
    public void removeClient( final int deadClientid, final JClient deadClient ) {
        sendMessageOthers( deadClient, deadClientid + ":## " + deadClient.getUsername() + " has left the chat ##" );
        form.removeClient(deadClient.getUsername());
        client.remove(deadClient);
        System.out.println( "#### Client removed from client manager ####");
    }

}
