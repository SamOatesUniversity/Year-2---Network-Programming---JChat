package jserver;

import java.io.IOException;
import java.net.*;


/**
 *
 * @author SAM
 */
public class JTCPServer {

    private int port;
    private ServerSocket serverSocket;
    private boolean running;
    private JServerForm form;
    private JClientManager manager;
    

    public JTCPServer(int port, JServerForm form) {
        this.port = port;
        this.form = form;
        this.running = true;
        this.manager = new JClientManager( form );
    }

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
