package jserver;

import java.io.IOException;
import java.net.*;

/**
 *
 * @author SAM
 */
public class JTCPServer extends Thread {

    private int port;
    private ServerSocket serverSocket;
    private boolean running;
    private JClientManager manager;
    private DatagramSocket ss;
    
    

    public JTCPServer(int port, JClientManager manager) {
        this.port = port;
        this.running = true;
        this.manager = manager;
    }

    @Override
    public void run() {

        if (initServerSocket(port)) {

            System.out.println("#### TCP Server Started ####");

            if (initDatagramSocket(port)) {

                System.out.println("#### UDP Server Started ####");

                manager.start();

                while (running) {
                    JClient newClient = waitForClient();
                    newClient.start();
                    manager.addClient(newClient);
                }
                              
            }
        }
    }

    public JClient waitForClient() {
        System.out.println("#### Waiting on Client Connection ####");
        Socket socket = null;
        try {
            socket = serverSocket.accept();//TODO Being able to stop this thread when application finish

        } catch (IOException e) {
            e.printStackTrace();
        }
        return new JClient(socket, ss);
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

    public boolean initDatagramSocket(int port) {
        try {
            ss = new DatagramSocket(port);

        } catch (BindException e) {
            System.err.println(" \n UDP PORT " + port + "ALREADY IN USED" + e);
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (ss == null) {
                System.err.println("\n \n SOCKET CREATION IMPOSSIBLE....");
                return false;
            }
        }
        return true;
    }
}
