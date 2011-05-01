package jserver;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;

/**
 *
 * @author J9060283
 */
/**
 * A storage place for TCP clients
 */
public class JClient extends Thread {

    private String name;
    private Socket socket;
    private boolean running;
    private ArrayList<String> message;

    /**
     * Initialise a new client, for TCP connection.
    @param  socket  a TCP Socket used for connection.
     *
    @return      none.
     *
     */
    public JClient(Socket socket) {
        this.running = true;
        this.message = new ArrayList<String>();
        this.socket = socket;
        this.name = recieveMessage();
        System.out.println("#### " + this.name + " Has Joined the Chat ####");
        sendMessage("Hello and welcome");
    }

    /**
     * The main loop in the JClient thread.
     * While the thread is running, receive new messages and
     * add them to the Clients message queue.
     *
     */
    @Override
    public void run() {
        while (running) {
            String newMessage = recieveMessage();
            if (!newMessage.isEmpty()) {
                message.add(newMessage);
            }
        }
    }

    /**
     * Sends a message to the server.
    @param  message  a String containing the message to be sent.
     *
    @return      none
     *
     */
    public void sendMessage(String message) {
        if (running) {
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

    /**
     * Receive a message from the server.
    @return      a String containing the received message.
     *
     */
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

        if (message == null) {
            message = "";
            running = false;
        }

        return message;
    }

    /**
     * Get whether the client thread is running or not
    @return      a boolean stating true if the thread is running, false otherwise.
     *
     */
    public boolean isRunning() {
        return running;
    }

    /**
     * Get whether the client has any messages in its message queue
    @return      a boolean stating true if there are messages queued.
     *
     */
    public boolean hasMessage() {
        return !message.isEmpty();
    }

    /**
     * Gets the next message in the message queue. Then removes the
     * message from the queue.
    @return      a String of the next message.
     *
     */
    public String getMessage() {
        String nextMessage = message.get(0);
        message.remove(0);
        return nextMessage;
    }

    /**
     * Get the users name
    @return      a String holding the users name.
     *
     */
    public String getUsername() {
        return name;
    }

    /**
     * Set the users name
    @return      none.
     *
     */
    public void setUsername(String name) {
        this.name = name;
    }
}
