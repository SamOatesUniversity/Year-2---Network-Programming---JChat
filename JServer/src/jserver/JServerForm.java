package jserver;

import java.awt.Rectangle;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.ListModel;

/**
 *
 * @author SAM
 */
public class JServerForm extends javax.swing.JFrame {

    public JServerForm() {
        initComponents();

        warned = new ArrayList<String>();
        slaped = new ArrayList<String>();
        kicked = new ArrayList<String>();
    }

    public void addClient(String clientName) {
        ListModel model = jList_Current_Users.getModel();

        DefaultListModel newmodel = new DefaultListModel();
        for( int i = 0; i < model.getSize(); i++ ) {
            newmodel.add(i, model.getElementAt(i));
        }
        newmodel.add(model.getSize(), clientName);

        jList_Current_Users.setModel(newmodel);

        serverMessage( clientName + " has joined the chat" );
    }

    public void removeClient( String clientName ) {
        ListModel model = jList_Current_Users.getModel();

        DefaultListModel newmodel = new DefaultListModel();
        int c = 0;
        for( int i = 0; i < model.getSize(); i++ ) {
            if( !model.getElementAt(i).toString().equals(clientName) ) {
                newmodel.add(c, model.getElementAt(i));
                c++;
            }
        }

        jList_Current_Users.setModel(newmodel);

        serverMessage( clientName + " has left the chat" );
    }

    public void addMessage( String user, String message ) {
        jTextArea_MessageLog.append( user + " : " + message + "\n");
        jTextArea_MessageLog.scrollRectToVisible(
                new Rectangle(0,jTextArea_MessageLog.getHeight(),1,1) );
    }

    private void serverMessage( String message ) {
        jTextArea_MessageLog.append( "## " + message + " ##\n");
        jTextArea_MessageLog.scrollRectToVisible(
                new Rectangle(0,jTextArea_MessageLog.getHeight(),1,1) );
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane_Current_Users = new javax.swing.JScrollPane();
        jList_Current_Users = new javax.swing.JList();
        jLabel_Current_Users = new javax.swing.JLabel();
        jButton_Warn = new javax.swing.JButton();
        jButton_Slap = new javax.swing.JButton();
        jButton_Kick = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea_MessageLog = new javax.swing.JTextArea();
        jLabel_MessageLog = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("JServer");
        setLocationByPlatform(true);
        setName("frame_main"); // NOI18N
        setResizable(false);

        jScrollPane_Current_Users.setViewportView(jList_Current_Users);

        jLabel_Current_Users.setText("Current Users");

        jButton_Warn.setText("Warn");
        jButton_Warn.setPreferredSize(new java.awt.Dimension(50, 23));
        jButton_Warn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Action_WarnClient(evt);
            }
        });

        jButton_Slap.setText("Kick");
        jButton_Slap.setPreferredSize(new java.awt.Dimension(50, 23));
        jButton_Slap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Action_KickClient(evt);
            }
        });

        jButton_Kick.setText("Slap");
        jButton_Kick.setPreferredSize(new java.awt.Dimension(50, 23));
        jButton_Kick.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Action_SlapClient(evt);
            }
        });

        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        jScrollPane1.setAutoscrolls(true);

        jTextArea_MessageLog.setColumns(20);
        jTextArea_MessageLog.setLineWrap(true);
        jTextArea_MessageLog.setRows(5);
        jScrollPane1.setViewportView(jTextArea_MessageLog);

        jLabel_MessageLog.setText("Message Log");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane_Current_Users, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButton_Warn, javax.swing.GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton_Kick, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton_Slap, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel_MessageLog)
                                .addGap(130, 130, 130))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addComponent(jLabel_Current_Users)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel_Current_Users)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton_Warn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton_Slap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton_Kick, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel_MessageLog)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane_Current_Users, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void Action_WarnClient(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Action_WarnClient
        if( jList_Current_Users.getModel().getSize() > 0 ) {
            int sel = jList_Current_Users.getSelectedIndex();
            if( sel >= 0 ) {
                String name = jList_Current_Users.getModel().getElementAt(sel).toString();
                serverMessage( name + " has recieved a warning!" );
                warned.add(name);
            }
        }
    }//GEN-LAST:event_Action_WarnClient

    private void Action_SlapClient(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Action_SlapClient
        if( jList_Current_Users.getModel().getSize() > 0 ) {
            int sel = jList_Current_Users.getSelectedIndex();
            if( sel >= 0 ) {
                String name = jList_Current_Users.getModel().getElementAt(sel).toString();
                serverMessage( name + " has been slapped by a big wet fish" );
                slaped.add(name);
            }
        }
    }//GEN-LAST:event_Action_SlapClient

    private void Action_KickClient(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Action_KickClient
        if( jList_Current_Users.getModel().getSize() > 0 ) {
            int sel = jList_Current_Users.getSelectedIndex();
            if( sel >= 0 ) {
                String name = jList_Current_Users.getModel().getElementAt(sel).toString();
                serverMessage( name + " has been kicked from the server" );
                kicked.add(name);
            }
        }
    }//GEN-LAST:event_Action_KickClient

    public boolean isWarning() {
        return !warned.isEmpty();
    }

    public boolean isSlaped() {
        return !slaped.isEmpty();
    }

    public boolean isKicked() {
        return !kicked.isEmpty();
    }

    public String getWarned() {
        String ret = warned.get(0);
        warned.remove(0);
        return ret;
    }

    public String getSlaped() {
        String ret = slaped.get(0);
        slaped.remove(0);
        return ret;
    }

    public String getKicked() {
        String ret = kicked.get(0);
        kicked.remove(0);
        return ret;
    }

    private ArrayList<String>           warned, slaped, kicked;

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_Kick;
    private javax.swing.JButton jButton_Slap;
    private javax.swing.JButton jButton_Warn;
    private javax.swing.JLabel jLabel_Current_Users;
    private javax.swing.JLabel jLabel_MessageLog;
    private javax.swing.JList jList_Current_Users;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane_Current_Users;
    private javax.swing.JTextArea jTextArea_MessageLog;
    // End of variables declaration//GEN-END:variables
}
