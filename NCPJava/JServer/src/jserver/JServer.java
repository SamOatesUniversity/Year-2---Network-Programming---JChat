package jserver;

/**
 *
 * @author J9060283
 */
/**
 * The main class for the server. Creates the UDP and TCP threads.
 */
public class JServer {

    private JTCPServer tcpServer;
    private JUDPServer udpServer;

    /**
     * The main entry point of the server.
     * This is also where the server GUI form is created.
    @param  args  startup arguments, this should be the server port.
     *
    @return      none.
     *
     */
    public static void main(String[] args) {

        int port = 5598;

        if (args.length > 0) {
            port = Integer.parseInt(args[0]);
        }

        System.out.println("Starting server on port : " + port);

        JServerForm mainForm = new JServerForm();
        mainForm.setVisible(true);

        JServer server = new JServer(mainForm, port);
    }

    /**
     * Creates both the UDP and TCP threads.
    @param  form  the servers GUI form.
     *
    @param  port  the port servers TCP and UDP should use.
     *
    @return      none.
     *
     */
    public JServer(JServerForm form, int port) {
        this.udpServer = new JUDPServer(port);
        udpServer.start();
        this.tcpServer = new JTCPServer(port, form);
        tcpServer.run();
    }
}
