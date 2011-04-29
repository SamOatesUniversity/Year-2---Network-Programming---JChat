package jserver;

/**
 *
 * @author SAM
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

        JServerForm mainForm = new JServerForm();
        mainForm.setVisible(true);

        JServer server = new JServer( mainForm, 5598 );
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
    public JServer( JServerForm form, int port )
    {
        this.udpServer = new JUDPServer( port, form );
        udpServer.start();
        this.tcpServer = new JTCPServer( port, form );
        tcpServer.run();
    }

}
