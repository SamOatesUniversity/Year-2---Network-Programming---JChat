/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jchat;

import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

/**
 *
 * @author j9060283
 */
public class JDrawablePanel extends javax.swing.JPanel {

    private ArrayList<Point> avatar;

    public JDrawablePanel() {
        avatar = new ArrayList<Point>();
    }

    public int addAvatar( Point pos ) {
        this.repaint();
        avatar.add(pos);
        return avatar.size() - 1;
    }

    public void updateAvatar( int id, Point pos ) {
        avatar.set(id, pos);
        this.repaint();
    }

    public void removeAvatar( int id ) {
        avatar.remove(id);
        this.repaint();
    }

    public Point getPos( int id ) {
        return avatar.get(id);
    }

    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g); // Do the original draw

        g.clearRect(0, 0, WIDTH, HEIGHT);

        // draw all people
        for( int i = 0; i < avatar.size(); i++ ) {
            for( int s = 20; s >= 20; s-- ) {
                g.drawRect(avatar.get(i).x, avatar.get(i).y, s, s);
            }
        }
    }

}
