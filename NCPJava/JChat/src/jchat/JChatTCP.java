/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jchat;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Sam
 */
/**
 *The TCP Thread
 *
 */
public class JChatTCP extends Thread {

    private int port;
    private InetAddress ip;
    private Socket socket;
    private boolean running;
    private String name;
    private JChatForm form;
    private JChatUDP udp;
    private DatagramSocket udpsocket;

    /**
     *Constructor of the TCP thread
     *
    @return none.
     */
    public JChatTCP() {
        running = true;
    }

    /**
     *Main run loop within the thread. Calls SendName function
     *then while the thread is running it receives the message
     *and adds them to the form. When the thread stops running it closes the TCP connections.
     *
    @return none.
     */
    @Override
    public void run() {

        sendName();

        while (running) {

            String newMessage = recieveMessage();
            System.out.println(newMessage);

            //id:## NAME has left the chat ##//
            if (newMessage.contains(" has left the chat ##")) {
                int pos = newMessage.indexOf(":##");
                String id = newMessage.substring(0, pos);
                System.out.println(id + " HAS LEFT!");

                newMessage = newMessage.substring(pos + 1);

                udp.removeClient(Integer.parseInt(id));
            }

            form.addMessage(newMessage);

            if (newMessage.equals("## You have been kicked by the server ##")) {
                running = false;
            }

        }

        closeConnection();
        form.enableChat(false);

    }

    /**
     *Connects to the TCP socket of the server
    @param name clients name
     *
    @param ip ip address of the server
     *
    @param port port of the server
     *
    @return none.
     */
    public void connect(String name, String ip, int port) {
        try {
            this.name = name;
            this.ip = InetAddress.getByName(ip);
            this.port = port;
        } catch (UnknownHostException ex) {
            Logger.getLogger(JChatTCP.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (createSocket()) {
            if (initClientUDPDatagramSocket()) {
                udp = new JChatUDP(udpsocket, form);
                udp.start();
                this.start();
                form.enableChat(true);
            }
        } else {
            form.errorMessage("Could not connect to server!", true);
        }
    }

    /**
     *Initiates the client socket for UDP connections to be recieved upon
     *
    @return boolean true if the socket has been created, false otherwise
     */
    public boolean initClientUDPDatagramSocket() {

        try {
            udpsocket = new DatagramSocket();
            udpsocket.connect(ip, port);

        } catch (ConnectException e) {

            System.err.println(" \n CONNECTION TO SERVER IMPOSSIBLE.. " + e);
            e.printStackTrace();
            return false;

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } finally {
            if (udpsocket == null) {
                System.err.println("DatagramSocket CREATION IMPOSSIBLE... ");
                return false;
            }
        }
        return true;
    }

    /**
     *Creates the TCP socket
     *
    @return boolean true if the socket was created, false otherwise
     */
    private boolean createSocket() {
        try {
            socket = new Socket(ip, port);
        } catch (ConnectException e) {
            System.err.println(" \n CONNECTION TO SERVER IMPOSSIBLE..is server running ?" + e);
            e.printStackTrace();
            return false;
        } catch (UnknownHostException e) {
            System.err.println(" \n CONNECTION TO SERVER 7000");
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } finally {
            if (socket == null) {
                System.err.println("SOCKET CREATION IMPOSSIBLE...Quitting now..");
                return false;
            }
        }

        return true;
    }

    /**
     *Closes the TCP connection
     *
    @return none.
     */
    private void closeConnection() {
        try {
            socket.close();
            System.out.println("Connection has been closed");
        } catch (IOException ex) {
            Logger.getLogger(JChatTCP.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     *Sends TCP message to the server
    @param message A string containing the message
     *
    @return none.
     */
    public void sendMessage(String Message) {
        if (running) {
            try {
                BufferedWriter clientOutStream = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                clientOutStream.write(Message + "\n");
                clientOutStream.flush();
            } catch (IOException e) {
                e.printStackTrace();
                running = false;
            }
        }
    }

    /**
     *Sends the name to the server
     *
    @return none.
     */
    private void sendName() {
        if (running) {
            try {
                BufferedWriter clientOutStream = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                clientOutStream.write(name + "\n");
                clientOutStream.flush();
            } catch (IOException e) {
                e.printStackTrace();
                running = false;
            }
        }
    }

    /**
     *Recieves the TCP message from the server
     *
    @return String the message received
     */
    private String recieveMessage() {
        String message = "";

        try {
            BufferedReader serverInStream = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            message = serverInStream.readLine();
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
     *A setter for the GUI
    @param form a JChatForm wished to be used as the GUI
     *
    @return none.
     */
    public void setForm(JChatForm form) {
        this.form = form;
    }
}
