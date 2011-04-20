package jserver;

/**
 *
 * @author SAM
 */
public class JServer {

    public static void main(String[] args) {

        JServerForm mainForm = new JServerForm();
        mainForm.setVisible(true);

        JServer server = new JServer( mainForm );
    }


    public JServer( JServerForm form )
    {
        JClientManager clientManager = new JClientManager( form );
        JTCPServer tcpServer = new JTCPServer( 5598, clientManager );
        tcpServer.start();        
    }

}
