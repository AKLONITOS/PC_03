/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Interfaz;

import java.awt.BorderLayout;

/**
 *
 * @author EDU
 */
public class Inicio extends javax.swing.JPanel {
    
    
    public Inicio() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        btn_Venta = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 3, 36)); // NOI18N
        jLabel1.setText("Publicidad");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(507, 90, -1, -1));

        btn_Venta.setText("venta");
        btn_Venta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_VentaActionPerformed(evt);
            }
        });
        add(btn_Venta, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 320, -1, -1));

        jButton2.setText("consulta");
        add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 540, -1, -1));
    }// </editor-fold>//GEN-END:initComponents

    private void btn_VentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_VentaActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_btn_VentaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_Venta;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}