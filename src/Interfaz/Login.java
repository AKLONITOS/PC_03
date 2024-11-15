/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Interfaz;

import Clases.Cuenta;
import Conexiones.Conexion;
import FuncionamientoTablas.TablaCuenta;
import java.io.FileInputStream;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.swing.SwingUtilities;


public class Login extends javax.swing.JFrame {
    int i;
    Cuenta pro=new Cuenta();
    TablaCuenta proDao=new TablaCuenta();

    public Login() {
        initComponents();
        inicializarDocumentListener();
        setLocationRelativeTo(null);
    }
    private void inicializarDocumentListener() {
        DocumentListener listener = new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                actualizarBoton();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                actualizarBoton();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                actualizarBoton();
            }
        };

        
        txt_usuario.getDocument().addDocumentListener(listener);
    }

    
    
    

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        txt_usuario = new javax.swing.JTextField();
        txt_contra = new javax.swing.JTextField();
        jcb_recordar = new javax.swing.JCheckBox();
        lbl_erroneoContraseña = new javax.swing.JLabel();
        lbl_erroneoUsuario = new javax.swing.JLabel();
        btn_ingresar = new javax.swing.JButton();
        btn_olvido = new javax.swing.JButton();
        btn_crear = new javax.swing.JToggleButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txt_usuario.setText("USUARIO");
        txt_usuario.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_usuarioFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_usuarioFocusLost(evt);
            }
        });
        txt_usuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_usuarioActionPerformed(evt);
            }
        });
        jPanel1.add(txt_usuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 150, 340, 37));

        txt_contra.setText("CONTRASEÑA");
        txt_contra.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_contraFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_contraFocusLost(evt);
            }
        });
        txt_contra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_contraActionPerformed(evt);
            }
        });
        jPanel1.add(txt_contra, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 230, 340, 37));

        jcb_recordar.setText("RECORDAR CONTRASEÑA");
        jPanel1.add(jcb_recordar, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 300, -1, -1));
        jPanel1.add(lbl_erroneoContraseña, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 270, 159, 22));
        jPanel1.add(lbl_erroneoUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 190, 160, 26));

        btn_ingresar.setText("INGRESAR");
        btn_ingresar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_ingresarMouseClicked(evt);
            }
        });
        btn_ingresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ingresarActionPerformed(evt);
            }
        });
        jPanel1.add(btn_ingresar, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 360, 162, -1));

        btn_olvido.setFont(new java.awt.Font("Yu Gothic UI Light", 1, 12)); // NOI18N
        btn_olvido.setText("¿Olvidó su contraseña?");
        btn_olvido.setBorder(null);
        jPanel1.add(btn_olvido, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 430, 240, 29));

        btn_crear.setText("CREAR CUENTA");
        btn_crear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_crearActionPerformed(evt);
            }
        });
        jPanel1.add(btn_crear, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 510, 162, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 481, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(59, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(49, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 562, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    private void btn_crearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_crearActionPerformed

        if (btn_crear.isEnabled()) {

            CrearCuenta abrir = new CrearCuenta();
            abrir.setVisible(true);
            this.dispose();
        }
    

    }//GEN-LAST:event_btn_crearActionPerformed

    private void btn_ingresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ingresarActionPerformed
        // TODO add your handling code here:
        String usuario = txt_usuario.getText().trim();
        String contraseña = txt_contra.getText().trim();
        
        Conexion cn = new Conexion();
        boolean loginExitoso = false;

        try {
            List<Cuenta> listaCuentas = proDao.listaDeUsuariosYClaves();
            for (Cuenta cuenta : listaCuentas) {
                if (cuenta.getUsuario().equals(usuario) && cuenta.getClave().equals(contraseña)) {
                    loginExitoso = true;break;
                }
            }
            //para que se dirigana ala ubicacion del Jframe osea menu
            if (loginExitoso) {
                JOptionPane.showMessageDialog(this, "Inicio de sesión exitoso");
                Menu1 abrir = new Menu1();
                abrir.setVisible(true);
                this.dispose();  // Cierrar este Jframe
            } else {
                JOptionPane.showMessageDialog(this, "Usuario o contraseña incorrectos");
                txt_usuario.setText("");
                txt_contra.setText("");
                txt_usuario.requestFocus();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al conectar con la base de datos: " + e.getMessage());
        }
        
        
            
        
    
    }//GEN-LAST:event_btn_ingresarActionPerformed

    private void btn_ingresarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_ingresarMouseClicked
        // TODO add your handling code here:
        String usuario = "Administrador";
        String contra = "123456";

        String Pass=new String(txt_contra.getText());

        if(txt_usuario.getText().equals(usuario)&& Pass.equals(contra)){
            Menu1 abrir =new Menu1();
            abrir.setVisible(true);
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Usuario / Contraseña incorrecta ("+i+")");
            txt_usuario.setText("USUARIO");
            txt_contra.setText("CONTRASEÑA");
            i++;
        }
        if (i>=4){
            dispose();
        }
    }//GEN-LAST:event_btn_ingresarMouseClicked

    private void txt_contraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_contraActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_contraActionPerformed

    private void txt_contraFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_contraFocusLost
        // TODO add your handling code here:
        if (txt_contra.getText().isEmpty()) {
            txt_contra.setText("CONTRASEÑA");
        }
    }//GEN-LAST:event_txt_contraFocusLost

    private void txt_contraFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_contraFocusGained
        // TODO add your handling code here:
        if (txt_contra.getText().equals("CONTRASEÑA")) {
            txt_contra.setText("");
        }
    }//GEN-LAST:event_txt_contraFocusGained

    private void txt_usuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_usuarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_usuarioActionPerformed

    private void txt_usuarioFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_usuarioFocusLost
        // TODO add your handling code here:
        if (txt_usuario.getText().isEmpty()) {
            txt_usuario.setText("USUARIO");
        }
    }//GEN-LAST:event_txt_usuarioFocusLost

    private void txt_usuarioFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_usuarioFocusGained
        // TODO add your handling code here:
        if (txt_usuario.getText().equals("USUARIO")) {
            txt_usuario.setText("");
        }
    }//GEN-LAST:event_txt_usuarioFocusGained

        private void actualizarBoton() {
            String usuario = txt_usuario.getText().trim();
            List<Cuenta> listaCuentas = proDao.obtenerUsuariosYRoles();
            boolean esAdministrador = false;
            for (Cuenta cuenta : listaCuentas) {
                if (cuenta.getUsuario().equals(usuario) && cuenta.getRol().equals("Admin")) {
                    esAdministrador = true;break; 
                }
           }
           btn_crear.setEnabled(esAdministrador);
            
        }
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
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                SwingUtilities.invokeLater(() -> {
        new Login().setVisible(true); 
    });
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton btn_crear;
    private javax.swing.JButton btn_ingresar;
    private javax.swing.JButton btn_olvido;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JCheckBox jcb_recordar;
    private javax.swing.JLabel lbl_erroneoContraseña;
    private javax.swing.JLabel lbl_erroneoUsuario;
    private javax.swing.JTextField txt_contra;
    private javax.swing.JTextField txt_usuario;
    // End of variables declaration//GEN-END:variables
}
