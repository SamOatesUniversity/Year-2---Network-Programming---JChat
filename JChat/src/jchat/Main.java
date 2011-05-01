/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jchat;

/**
 *
 * @author J9060283
 */
/**
 *The Main entry point of the Client
 *
 */
public class Main {

    /**
     *Main entry point of client, creates TCP and the form
    @param args a list of startup arguments
     *
    @return none.
     */
    public static void main(String[] args) {

        JChatTCP tcp = new JChatTCP();
        JChatForm form = new JChatForm(tcp);
        form.setVisible(true);
        tcp.setForm(form);

    }
}
