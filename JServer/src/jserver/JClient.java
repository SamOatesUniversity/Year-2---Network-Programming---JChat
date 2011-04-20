package jserver;

import java.awt.Point;
import java.io.*;
import java.net.DatagramSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author SAM
 */
public class JClient extends Thread {

    private String              name;
    private Socket              tcpsocket;
    private boolean             running;
    private ArrayList<String>   message;
    private JUDPLoop            udp;


    public JClient( Socket tcpsocket, DatagramSocket udpsocket ) {
        this.running = true;
        this.message = new ArrayList<String>();
        this.tcpsocket = tcpsocket;

        udp = new JUDPLoop( udpsocket );
        udp.start();

        this.name = recieveMessage();
        System.out.println("#### "+ this.name + " Has Joined the Chat ####");
        try {
            sendMessage("Hello and welcome");
        } catch (IOException ex) {
            Logger.getLogger(JClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void run() {
        while( running ) {
            String newMessage = recieveMessage();
            if( !newMessage.isEmpty() ) {
                message.add(newMessage);
            }
        }
        udp.kill();
    }

    public Point getAvatar() {
        return udp.getAvatar();
    }

    public void udpSend( Point p ) {
        udp.send( p );
    }

    public void sendMessage( String message ) throws IOException {
        if( running ) {
            BufferedWriter clientOutStream = new BufferedWriter(new OutputStreamWriter(tcpsocket.getOutputStream()));
            clientOutStream.write(message + "\n");
            clientOutStream.flush();
        }
    }

    private String recieveMessage() {
        String message = "";

        try {
            BufferedReader serverInStream = new BufferedReader(new InputStreamReader(tcpsocket.getInputStream()));
            message = serverInStream.readLine();
        } catch (IOException e) {
            running = false;
            message = "";
        }

        if( message == null ) {
            message = "";
            running = false;
        }

        return message;
    }

    public boolean isRunning() {
        return running;
    }

    public boolean hasMessage() {
        return !message.isEmpty();
    }

    public String getMessage() {
        String nextMessage = message.get(0);
        message.remove(0);
        return nextMessage;
    }

    public String getUsername() {
        return name;
    }

    public void setUsername( String name ) {
        this.name = name;
    }
}
