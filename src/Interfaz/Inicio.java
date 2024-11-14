/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Interfaz;

import FuncionamientoTablas.TablaProductos;
import java.awt.BorderLayout;
import Interfaz.Menu;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.Timer;

/**
 *
 * @author EDU
 */
public class Inicio extends javax.swing.JPanel {
    private Timer timer;
    private int currentIndex = 0;
    private List<byte[]> bannerImages;
    
    
    public Inicio() {
        initComponents();
        TablaProductos tablaProductos = new TablaProductos();
        bannerImages = tablaProductos.obtenerImagenesParaBanner();
        timer = new Timer(3000, new ActionListener() { // Cambia cada 3 segundos
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!bannerImages.isEmpty()) {
                    currentIndex = (currentIndex + 1) % bannerImages.size();
                    cambiarImagen();
                }
            }
        });
        timer.start();
    }
    private void cambiarImagen() {
        if (!bannerImages.isEmpty()) {
            byte[] imgBytes = bannerImages.get(currentIndex);
            ImageIcon icon = new ImageIcon(imgBytes);
            Image img = icon.getImage().getScaledInstance(BannerImagens1.getWidth(), BannerImagens1.getHeight(), Image.SCALE_SMOOTH);
            BannerImagens1.setIcon(new ImageIcon(img));
        }
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btn_Venta = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        PestañaVinculacion = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        BannerImagens1 = new javax.swing.JLabel();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btn_Venta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/venta.png"))); // NOI18N
        btn_Venta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_VentaActionPerformed(evt);
            }
        });
        add(btn_Venta, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 450, 130, 120));

        jButton2.setText("consulta");
        add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 630, -1, -1));
        add(PestañaVinculacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 370, 1150, 320));

        jLabel2.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        jLabel2.setText("VENTA");
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 420, -1, -1));

        BannerImagens1.setText("Banner");
        BannerImagens1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        add(BannerImagens1, new org.netbeans.lib.awtextra.AbsoluteConstraints(127, 40, 1500, 330));
    }// </editor-fold>//GEN-END:initComponents

    private void btn_VentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_VentaActionPerformed
        // TODO add your handling code here:
        Venta ven = new Venta ();
        ven.setSize(1420,950);
        ven.setLocation(0,0);
        PestañaVinculacion.removeAll();
        PestañaVinculacion.add(ven, BorderLayout.CENTER);
        PestañaVinculacion.revalidate();
        PestañaVinculacion.repaint();
        
    }//GEN-LAST:event_btn_VentaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel BannerImagens1;
    private javax.swing.JPanel PestañaVinculacion;
    private javax.swing.JButton btn_Venta;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel2;
    // End of variables declaration//GEN-END:variables
}
