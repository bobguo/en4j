/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * SyncToolbarJPanel.java
 *
 * Created on Feb 14, 2010, 3:39:05 PM
 */
package com.rubenlaguna.en4j.sync;

import com.rubenlaguna.en4j.interfaces.SynchronizationService;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import org.openide.util.Lookup;

/**
 *
 * @author Ruben Laguna <ruben.laguna@gmail.com>
 */
public class SyncToolbarJPanel extends javax.swing.JPanel {

    private final SynchronizationService sservice;

    /** Creates new form SyncToolbarJPanel */
    public SyncToolbarJPanel() {
        initComponents();
        jLabel1.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                new SyncAction().actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "SYNC"));
            }
        });
        sservice = getSyncService();
        jLabel5.setIcon(null);
        sservice.addPropertyChangeListener(new PropertyChangeListener() {

            public void propertyChange(PropertyChangeEvent evt) {
                if (evt.getPropertyName().equals(SynchronizationServiceImpl.PROP_SYNCFAILED)) {

                    if(evt.getNewValue().equals(Boolean.TRUE)) {
                        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/rubenlaguna/en4j/sync/fatal_error.png"))); // NOI18N
                    } else {
                        jLabel5.setIcon(null);
                    }
                }
            }
        });


    }

    private SynchronizationService getSyncService() {
        return Lookup.getDefault().lookup(SynchronizationService.class);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        bindingGroup = new org.jdesktop.beansbinding.BindingGroup();

        syncServiceBean1 = new com.rubenlaguna.en4j.sync.SyncServiceBean();
        label1 = new java.awt.Label();
        jLabel4 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();

        label1.setText(org.openide.util.NbBundle.getMessage(SyncToolbarJPanel.class, "SyncToolbarJPanel.label1.text")); // NOI18N

        jLabel4.setText(org.openide.util.NbBundle.getMessage(SyncToolbarJPanel.class, "SyncToolbarJPanel.jLabel4.text")); // NOI18N

        setMaximumSize(new java.awt.Dimension(142, 70));

        org.jdesktop.beansbinding.Binding binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, syncServiceBean1, org.jdesktop.beansbinding.ELProperty.create("${pendingRemoteUpdateNotes}"), jLabel1, org.jdesktop.beansbinding.BeanProperty.create("text"), "sservice");
        binding.setSourceNullValue("Sync");
        binding.setSourceUnreadableValue("Sync");
        bindingGroup.addBinding(binding);

        jLabel2.setText(org.openide.util.NbBundle.getMessage(SyncToolbarJPanel.class, "SyncToolbarJPanel.jLabel2.text")); // NOI18N

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText(org.openide.util.NbBundle.getMessage(SyncToolbarJPanel.class, "SyncToolbarJPanel.jLabel3.text")); // NOI18N

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/rubenlaguna/en4j/sync/fatal_error.png"))); // NOI18N
        jLabel5.setText(org.openide.util.NbBundle.getMessage(SyncToolbarJPanel.class, "SyncToolbarJPanel.jLabel5.text")); // NOI18N

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(30, 30, 30)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createSequentialGroup()
                        .add(jLabel2)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 34, Short.MAX_VALUE)
                        .add(jLabel1))
                    .add(layout.createSequentialGroup()
                        .add(jLabel5)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jLabel3, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                .addContainerGap(32, Short.MAX_VALUE)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel3)
                    .add(jLabel5))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel1)
                    .add(jLabel2))
                .add(22, 22, 22))
        );

        bindingGroup.bind();
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    javax.swing.JLabel jLabel1;
    javax.swing.JLabel jLabel2;
    javax.swing.JLabel jLabel3;
    javax.swing.JLabel jLabel4;
    javax.swing.JLabel jLabel5;
    java.awt.Label label1;
    com.rubenlaguna.en4j.sync.SyncServiceBean syncServiceBean1;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables
}