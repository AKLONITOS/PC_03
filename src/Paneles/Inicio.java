/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Paneles;

import FuncionamientoTablas.TablaProductos;
import java.awt.BorderLayout;
import Interfaz.Menu1;
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

        roundPanel1 = new javaswingdev.swing.RoundPanel();
        BannerImagens1 = new javax.swing.JLabel();
        PestañaVinculacion = new javax.swing.JPanel();
        btn_Venta = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();

        roundPanel1.setBackground(new java.awt.Color(204, 204, 204));
        roundPanel1.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
        roundPanel1.setRound(10);

        BannerImagens1.setText("Banner");
        BannerImagens1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout PestañaVinculacionLayout = new javax.swing.GroupLayout(PestañaVinculacion);
        PestañaVinculacion.setLayout(PestañaVinculacionLayout);
        PestañaVinculacionLayout.setHorizontalGroup(
            PestañaVinculacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1031, Short.MAX_VALUE)
        );
        PestañaVinculacionLayout.setVerticalGroup(
            PestañaVinculacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 637, Short.MAX_VALUE)
        );

        btn_Venta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/venta.png"))); // NOI18N
        btn_Venta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_VentaActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        jLabel2.setText("VENTA");

        jButton2.setText("consulta");

        javax.swing.GroupLayout roundPanel1Layout = new javax.swing.GroupLayout(roundPanel1);
        roundPanel1.setLayout(roundPanel1Layout);
        roundPanel1Layout.setHorizontalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(roundPanel1Layout.createSequentialGroup()
                        .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(roundPanel1Layout.createSequentialGroup()
                                .addGap(117, 117, 117)
                                .addComponent(btn_Venta, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(31, 31, 31))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(71, 71, 71))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundPanel1Layout.createSequentialGroup()
                                .addComponent(jButton2)
                                .addGap(51, 51, 51)))
                        .addComponent(PestañaVinculacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 575, Short.MAX_VALUE))
                    .addComponent(BannerImagens1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        roundPanel1Layout.setVerticalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addComponent(BannerImagens1, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(roundPanel1Layout.createSequentialGroup()
                        .addGap(178, 178, 178)
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(btn_Venta, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton2)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(roundPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                        .addComponent(PestañaVinculacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(roundPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(roundPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
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
    private javaswingdev.swing.RoundPanel roundPanel1;
    // End of variables declaration//GEN-END:variables
}
