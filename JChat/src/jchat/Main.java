/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jchat;

/**
 *
 * @author Sam
 */
public class Main {
    
    public static void main(String[] args) {
        
        JChatTCP tcp = new JChatTCP( );
        JChatForm form = new JChatForm( tcp );
        form.setVisible(true);
        tcp.setForm( form );
        
    }

}
