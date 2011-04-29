package jserver;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;

/**
 *
 * @author SAM
 */
public class JClient extends Thread {

    private String              name;
    private Socket              socket;
    private boolean             running, hasMessage;
    private ArrayList<String>   message;

    public JClient( Socket socket ) {
        this.running = true;
        this.message = new ArrayList<String>();
        this.socket = socket;
        this.name = recieveMessage();
        System.out.println("#### "+ this.name + " Has Joined the Chat ####");
        sendMessage( "Hello and welcome" );
    }

    @Override
    public void run() {
        while( running ) {
            String newMessage = recieveMessage();
            if( !newMessage.isEmpty() ) {
                message.add(newMessage);
            }
        }
    }

    public void sendMessage( String message ) {
        if( running ) {
            try {
                BufferedWriter clientOutStream = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                clientOutStream.write(message + "\n");
                clientOutStream.flush();
            } catch (IOException e) {
                e.printStackTrace();
                running = false;
            }
        }
    }

    private String recieveMessage() {
        String message = "";

        try {
            BufferedReader serverInStream = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            message = serverInStream.readLine();
        } catch (SocketException e) {
            running = false;
            message = "";
        } catch (IOException e) {
            e.printStackTrace();
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

    public Socket getSocket() {
        return socket;
    }

}
