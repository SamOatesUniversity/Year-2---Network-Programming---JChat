package jserver;

/**
 *
 * @author SAM
 */
public class JServer {

    private JServerForm form;
    private JTCPServer tcpServer;
    private JUDPServer udpServer;

    public static void main(String[] args) {

        JServerForm mainForm = new JServerForm();
        mainForm.setVisible(true);

        JServer server = new JServer( mainForm );
    }


    public JServer( JServerForm form )
    {
        this.form = form;
        this.udpServer = new JUDPServer( 5598, form );
        udpServer.start();
        this.tcpServer = new JTCPServer( 5598, form );
        tcpServer.run();
    }

}
