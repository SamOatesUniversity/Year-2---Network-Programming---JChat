package jserver;

import java.awt.Point;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author SAM
 */
public class JClientManager extends Thread {

    private ArrayList<JClient>  client;
    private boolean             running;
    private JServerForm         form;

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
                        removeClient( c );
                    }
                }

                //server action//
                if( form.isKicked() ) {
                    String cw = form.getKicked();
                    for( int i = 0; i < client.size(); i++ ) {
                        JClient c = client.get(i);
                        if( cw.equals(c.getUsername()) ) {
                            sendMessage( c, "## You have been kicked by the server ##" );
                            removeClient( c );
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


                //udp stuff//
                for( int i = 0; i < client.size(); i++ ) {
                    JClient c = client.get(i);
                    Point p = c.getAvatar();
                    udpSendOthers( c, p );
                }

            }
            
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                Logger.getLogger(JClientManager.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }

    }

    public void sendMessage( JClient c, String message ) {
        try {
            c.sendMessage(message);
        } catch (IOException ex) {
            Logger.getLogger(JClientManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void sendMessageAll( String message ) {
        for( int i = 0; i < client.size(); i++ ) {
            sendMessage( client.get(i), message );
        }
    }

    public void sendMessageOthers( JClient sender, String message ) {
        for( int i = 0; i < client.size(); i++ ) {
            JClient c = client.get(i);
            if( c != sender ) {
                sendMessage( c, message );
            }
        }
    }

    public void udpSend( JClient sender, int id, Point pos ) {
        sender.udpSend( id, pos );
    }

    public void udpSendOthers( JClient sender, Point pos ) {
        for( int i = 0; i < client.size(); i++ ) {
            JClient c = client.get(i);
            if( c != sender ) {
                udpSend( c, i, pos );
            }
        }
    }

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

    public void removeClient( final JClient deadClient ) {
        sendMessageOthers( deadClient, "## " + deadClient.getUsername() + " has left the chat ##" );
        form.removeClient(deadClient.getUsername());
        client.remove(deadClient);
        System.out.println( "#### Client removed from client manager ####");
    }

}
