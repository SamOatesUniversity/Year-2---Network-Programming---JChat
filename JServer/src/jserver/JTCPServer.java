package jserver;

import java.io.IOException;
import java.net.*;


/**
 *
 * @author J9060283
 */

/**
 * The controller for the TCP connections
 */
public class JTCPServer {

    private int port;
    private ServerSocket serverSocket;
    private boolean running;
    private JClientManager manager;

    /**
     * Initialise the TCP sockets and creates a Client Manager for the
     * TCP clients.
    @param  port  the Port to be used when creating the TCP socket.
     *
    @param  form  the servers GUI form.
     *
    @return      none.
     *
     */
    public JTCPServer(int port, JServerForm form) {
        this.port = port;
        this.running = true;
        this.manager = new JClientManager( form );
    }
    
    /**
     * Loops on a separate thread, waiting on new TCP client connections.
     * then adding the new clients to the client manager.
     */
    public void run() {

        if (initServerSocket(port)) {

            manager.start();
            System.out.println("#### Server Started ####");

            while (running) {
                JClient newClient = waitForClient();
                newClient.start();
                manager.addClient( newClient );
            }

        }
    }

    /**
     * Waits for a new TCP client to connect.
    @return   JClient   The new JClient that has connected.
     *
     */
    public JClient waitForClient() {
        System.out.println("#### Waiting on Client Connection ####");
        Socket socket = new Socket();
        try {
            socket = serverSocket.accept();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return new JClient(socket);
    }

    /**
     * Waits for a new TCP client to connect.
    @param port The port to initialise the TCP socket on.
     *
    @return   boolean   Whether the socket was created successfully.
     *
     */
    public boolean initServerSocket(int Port) {
        try {
            serverSocket = new ServerSocket(Port);
        } catch (BindException e) {
            System.err.println(" \n TCP PORT " + Port + "ALREADY IN USED" + e);
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (serverSocket == null) {
                System.err.println("\n \n SOCKET CREATION IMPOSSIBLE...");
                return false;
            }
        }
        return true;
    }
}
