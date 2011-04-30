/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jserver;

/**
 * A storage place for UDP client data.
 */
public class JUDPClient {
    public String           ip;
    public int              port;
    public String           latest_message;
    public int              time;
    public boolean          has_message;
}
