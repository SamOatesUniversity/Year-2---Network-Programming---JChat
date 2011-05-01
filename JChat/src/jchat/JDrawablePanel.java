/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jchat;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

/**
 *
 * @author j9060283
 */
/**
 *The Panel on which the Avatars are drawn
 *
 */
public class JDrawablePanel extends javax.swing.JPanel {

    private ArrayList<Point> avatar;

    public JDrawablePanel() {
        avatar = new ArrayList<Point>();
    }

    /**
     *Adds an avatar to the list of avatars to draw
    @param pos The initial position of the avatar
     *
    @return int The id of the newly added avatar
     */
    public int addAvatar(Point pos) {
        this.repaint();
        avatar.add(pos);
        return avatar.size() - 1;
    }

    /**
     *Updates the position of an avatar
    @param id The id of the avatar
     *
    @param pos The new position of the avatar
     *
    @return none.
     */
    public void updateAvatar(int id, Point pos) {
        avatar.set(id, pos);
        this.repaint();
    }

    /**
     *Removes an avatar from the draw list
    @param id The id of the avatar to remove
     *
    @return none.
     */
    public void removeAvatar(int id) {
        avatar.remove(id);
        this.repaint();
    }

    /**
     *Returns the position of an avatar at a given id
    @param id The id of the avatar
     *
    @return Point The position of the avatar
     */
    public Point getPos(int id) {
        return avatar.get(id);
    }

    /**
     *The draw function. This clears the panel then loops
     *through drawing all avatars
    @param graphics
     *
    @return none.
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g); // Do the original draw

        g.clearRect(0, 0, WIDTH, HEIGHT);

        // draw all people
        for (int i = 0; i < avatar.size(); i++) {
            for (int s = 20; s >= 0; s--) {
                if (i == 0) {
                    g.setColor(Color.red);
                } else {
                    g.setColor(Color.black);
                }
                g.drawRect(avatar.get(i).x, avatar.get(i).y, s, s);
            }
        }
    }
}
