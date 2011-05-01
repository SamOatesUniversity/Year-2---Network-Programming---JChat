/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * JChatForm.java
 *
 * Created on 30-Mar-2011, 13:48:09
 */
package jchat;

import java.awt.Point;
import java.awt.Rectangle;
import javax.swing.JTextField;

/**
 *
 * @author Sam
 */
public class JChatForm extends javax.swing.JFrame {

    /** Creates new form JChatForm */
    public JChatForm(JChatTCP tcp) {
        initComponents();
        this.tcp = tcp;
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel_Name = new javax.swing.JLabel();
        jTextField_UserName = new javax.swing.JTextField();
        jButton_Connect = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea_Input = new javax.swing.JTextArea();
        jButton_Send = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea_Output = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        jTextField_IP1 = new javax.swing.JTextField();
        jTextField_IP2 = new javax.swing.JTextField();
        jTextField_IP3 = new javax.swing.JTextField();
        jTextField_IP4 = new javax.swing.JTextField();
        jTextField_IP5 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jPanel_Drawable = new JDrawablePanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("JChat");
        setLocationByPlatform(true);
        setResizable(false);

        jLabel_Name.setText("Your Name");

        jButton_Connect.setText("Connect");
        jButton_Connect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Action_Connect(evt);
            }
        });

        jTextArea_Input.setColumns(20);
        jTextArea_Input.setLineWrap(true);
        jTextArea_Input.setRows(3);
        jTextArea_Input.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextArea_InputKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(jTextArea_Input);

        jButton_Send.setText("Send");
        jButton_Send.setEnabled(false);
        jButton_Send.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Action_Send(evt);
            }
        });

        jTextArea_Output.setColumns(20);
        jTextArea_Output.setEditable(false);
        jTextArea_Output.setLineWrap(true);
        jTextArea_Output.setRows(10);
        jScrollPane2.setViewportView(jTextArea_Output);

        jLabel1.setText("Server Address");

        jTextField_IP1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField_IP1.setText("127");
        jTextField_IP1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                Action_GainFocus(evt);
            }
        });

        jTextField_IP2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField_IP2.setText("0");
        jTextField_IP2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                Action_GainFocus(evt);
            }
        });

        jTextField_IP3.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField_IP3.setText("0");
        jTextField_IP3.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                Action_GainFocus(evt);
            }
        });

        jTextField_IP4.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField_IP4.setText("1");
        jTextField_IP4.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                Action_GainFocus(evt);
            }
        });

        jTextField_IP5.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField_IP5.setText("5598");
        jTextField_IP5.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                Action_GainFocus(evt);
            }
        });

        jLabel2.setText(".");

        jLabel3.setText(".");

        jLabel4.setText(".");

        jLabel5.setText(":");

        jPanel_Drawable.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jPanel_Drawable.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jPanel_DrawableMouseDragged(evt);
            }
        });

        javax.swing.GroupLayout jPanel_DrawableLayout = new javax.swing.GroupLayout(jPanel_Drawable);
        jPanel_Drawable.setLayout(jPanel_DrawableLayout);
        jPanel_DrawableLayout.setHorizontalGroup(
            jPanel_DrawableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 296, Short.MAX_VALUE)
        );
        jPanel_DrawableLayout.setVerticalGroup(
            jPanel_DrawableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 332, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 566, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton_Send, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jLabel_Name)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField_UserName, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField_IP1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField_IP2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField_IP3, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField_IP4, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField_IP5, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton_Connect, javax.swing.GroupLayout.DEFAULT_SIZE, 137, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 335, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel_Drawable, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel_Name)
                    .addComponent(jTextField_UserName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_Connect)
                    .addComponent(jTextField_IP5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField_IP4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(jTextField_IP1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jTextField_IP2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jTextField_IP3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel_Drawable, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 332, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton_Send, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void Action_Connect(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Action_Connect
        name = jTextField_UserName.getText();
        if (!name.isEmpty()) {
            System.out.println("your name is : " + name);

            String ip = jTextField_IP1.getText() + "."
                    + jTextField_IP2.getText() + "."
                    + jTextField_IP3.getText() + "."
                    + jTextField_IP4.getText();

            int port = Integer.parseInt(jTextField_IP5.getText().trim());

            jButton_Connect.setEnabled(false);
            jTextField_IP1.setEnabled(false);
            jTextField_IP2.setEnabled(false);
            jTextField_IP3.setEnabled(false);
            jTextField_IP4.setEnabled(false);
            jTextField_IP5.setEnabled(false);
            jTextField_UserName.setEditable(false);

            tcp.connect(name, ip, port);

        }
    }//GEN-LAST:event_Action_Connect

    private void Action_Send(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Action_Send
        String message = jTextArea_Input.getText();
        if (!message.isEmpty()) {
            tcp.sendMessage(message);
            jTextArea_Input.setText("");
            jTextArea_Output.append(name + " : " + message + "\n");
        }
    }//GEN-LAST:event_Action_Send

    private void Action_GainFocus(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_Action_GainFocus
        javax.swing.JTextField fld = (JTextField) evt.getSource();
        fld.setText("");
    }//GEN-LAST:event_Action_GainFocus

    private void jTextArea_InputKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextArea_InputKeyReleased
        if (evt.getKeyCode() == evt.VK_ENTER) {
            String message = jTextArea_Input.getText().trim();
            if (!message.isEmpty()) {
                tcp.sendMessage(message);
                jTextArea_Input.setText("");
                addMessage(name + " : " + message);
            }
        }
    }//GEN-LAST:event_jTextArea_InputKeyReleased

    private void jPanel_DrawableMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel_DrawableMouseDragged
        Point pos = new Point(evt.getPoint().x - 10, evt.getPoint().y - 10);
        if (pos.x > 0 && pos.y > 0 && pos.x < ((JDrawablePanel) jPanel_Drawable).getWidth() - 20
                && pos.y < ((JDrawablePanel) jPanel_Drawable).getHeight() - 20) {
            Point current_pos = ((JDrawablePanel) jPanel_Drawable).getPos(0);
            if (pos.x > current_pos.x - 20 && pos.y > current_pos.y - 20
                    && pos.x < current_pos.x + 40 && pos.y < current_pos.y + 40) {
                updateAvatar(0, pos);
            }
        }
    }//GEN-LAST:event_jPanel_DrawableMouseDragged

    public void addMessage(String message) {

        jTextArea_Output.append(message + "\n");

        jTextArea_Output.scrollRectToVisible(
                new Rectangle(0, jTextArea_Output.getHeight(), 1, 1));

    }

    public void enableChat(boolean enabled) {
        jButton_Send.setEnabled(enabled);
    }

    public int addAvatar(Point pos) {
        return ((JDrawablePanel) jPanel_Drawable).addAvatar(pos);
    }

    public void updateAvatar(int id, Point pos) {
        ((JDrawablePanel) jPanel_Drawable).updateAvatar(id, pos);
    }

    public void removeAvatar(int id) {
        ((JDrawablePanel) jPanel_Drawable).removeAvatar(id);
    }

    public Point getAvatar(int id) {
        return ((JDrawablePanel) jPanel_Drawable).getPos(id);
    }

    public void errorMessage(String message, boolean enableConnect) {
        jTextArea_Output.append(message + "\n");
        jButton_Connect.setEnabled(enableConnect);
    }
    private JChatTCP tcp;
    private String name;
    private int self_avatar;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_Connect;
    private javax.swing.JButton jButton_Send;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel_Name;
    private javax.swing.JPanel jPanel_Drawable;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextArea_Input;
    private javax.swing.JTextArea jTextArea_Output;
    private javax.swing.JTextField jTextField_IP1;
    private javax.swing.JTextField jTextField_IP2;
    private javax.swing.JTextField jTextField_IP3;
    private javax.swing.JTextField jTextField_IP4;
    private javax.swing.JTextField jTextField_IP5;
    private javax.swing.JTextField jTextField_UserName;
    // End of variables declaration//GEN-END:variables
}
