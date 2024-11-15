/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Interfaz;

import Paneles.Productos;
import Paneles.Inicio;
import java.awt.BorderLayout;
import javax.swing.JPanel;

/**
 *
 * @author EDU
 */
public class Menu1 extends javax.swing.JFrame {

    /**
     * Creates new form Menu
     */
    public Menu1() {
        initComponents();
        setLocationRelativeTo(null);
        
        Inicio p1= new Inicio() ;
        p1.setSize(1820, 770);
        p1.setLocation(0,0);
        PestañadeVinculacion.removeAll();
        PestañadeVinculacion.add(p1, BorderLayout.CENTER);
        PestañadeVinculacion.revalidate();
        PestañadeVinculacion.repaint();
        
        
        
    }
    private void cargarPanelInicio() {
        Inicio p1 = new Inicio();
        
    }
   

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        PestañadeVinculacion = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        btn_inicio = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        btn_Productos = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        PestañadeVinculacion.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout PestañadeVinculacionLayout = new javax.swing.GroupLayout(PestañadeVinculacion);
        PestañadeVinculacion.setLayout(PestañadeVinculacionLayout);
        PestañadeVinculacionLayout.setHorizontalGroup(
            PestañadeVinculacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1808, Short.MAX_VALUE)
        );
        PestañadeVinculacionLayout.setVerticalGroup(
            PestañadeVinculacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 798, Short.MAX_VALUE)
        );

        getContentPane().add(PestañadeVinculacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, 1810, 800));

        jLabel1.setText("carrito");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1730, 50, -1, -1));

        jLabel2.setText("usuario");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1560, 50, -1, -1));

        jLabel3.setText("venta");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1450, 50, -1, -1));

        jMenu1.setText("Home");

        btn_inicio.setText("Inicio");
        btn_inicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_inicioActionPerformed(evt);
            }
        });
        jMenu1.add(btn_inicio);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Mantenimiento");

        jMenuItem2.setText("Usuario");
        jMenu2.add(jMenuItem2);

        jMenuItem3.setText("Cliente");
        jMenu2.add(jMenuItem3);

        btn_Productos.setText("Productos");
        btn_Productos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ProductosActionPerformed(evt);
            }
        });
        jMenu2.add(btn_Productos);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_inicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_inicioActionPerformed
        // TODO add your handling code here:
        Inicio p1= new Inicio() ;
        p1.setSize(1820, 770);
        p1.setLocation(0,0);
        PestañadeVinculacion.removeAll();
        PestañadeVinculacion.add(p1, BorderLayout.CENTER);
        PestañadeVinculacion.revalidate();
        PestañadeVinculacion.repaint();
    }//GEN-LAST:event_btn_inicioActionPerformed

    private void btn_ProductosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ProductosActionPerformed
        // TODO add your handling code here:
        Productos p2= new Productos() ;
        p2.setSize(1820, 770);
        p2.setLocation(0,0);
        PestañadeVinculacion.removeAll();
        PestañadeVinculacion.add(p2, BorderLayout.CENTER);
        PestañadeVinculacion.revalidate();
        PestañadeVinculacion.repaint();
    }//GEN-LAST:event_btn_ProductosActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Menu1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Menu1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Menu1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Menu1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Menu1().setVisible(true);
                
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PestañadeVinculacion;
    private javax.swing.JMenuItem btn_Productos;
    private javax.swing.JMenuItem btn_inicio;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    // End of variables declaration//GEN-END:variables
}
