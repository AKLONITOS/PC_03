
package Interfaz;

import Clases.Cuenta;
import FuncionamientoTablas.Generador;
import FuncionamientoTablas.TablaCuenta;
import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;



public class CrearCuenta extends javax.swing.JFrame {
    Cuenta pro=new Cuenta();
    TablaCuenta proDao=new TablaCuenta();
    
    
    

    public CrearCuenta() {
        initComponents();
        
        generarUsuarioYClave();
        setLocationRelativeTo(null);
        btg_estado.add(b_activo);
        btg_estado.add(b_inactivo);
    }
    
    
    public void llenarCampos(String nombre, String apellido, String dni, String usuario, String clave, String correo, String estado, String rol, byte[] fotoBytes) {
        
        txt_nombre.setText(nombre);
        txt_apellido.setText(apellido);
        txt_dni.setText(dni);
        txt_usuario.setText(usuario);
        txt_clave.setText(clave);
        txt_correo.setText(correo);
        
        if (estado.equalsIgnoreCase("Activo")) {
            b_activo.setSelected(true);
        } else 
            if (estado.equalsIgnoreCase("Inactivo")) {
                b_inactivo.setSelected(true);
            }
        CB_ROL.setSelectedItem(rol);
        
        if (fotoBytes != null) {
            ImageIcon imageIcon = new ImageIcon(fotoBytes);
            Image image = imageIcon.getImage().getScaledInstance(110, 110, Image.SCALE_SMOOTH);
            imageIcon = new ImageIcon(image);
            lb_Foto.setIcon(imageIcon); 
        }
    }
    private void generarUsuarioYClave() {
        // Genera usuario y clave aleatorios
        String usuarioAleatorio = Generador.generarUsuario();
        String claveAleatoria = Generador.generarClave();

        pro.setUsuario(usuarioAleatorio);
        pro.setClave(claveAleatoria);
           
        
        txt_usuario.setText(usuarioAleatorio);
        txt_clave.setText(claveAleatoria);
    }

    public String getNombre() {
        return txt_nombre.getText();
    }

    public String getApellido() {
        return txt_apellido.getText();
    }



    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btg_estado = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        txt_nombre = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txt_apellido = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txt_dni = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txt_usuario = new javax.swing.JTextField();
        txt_clave = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txt_correo = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        CB_ROL = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        lb_Foto = new javax.swing.JLabel();
        btn_foto = new javax.swing.JButton();
        btn_agregar = new javax.swing.JButton();
        btn_editar = new javax.swing.JButton();
        btn_eliminar = new javax.swing.JButton();
        btn_nuevo = new javax.swing.JButton();
        btn_ListaUsuario = new javax.swing.JButton();
        txt_foto = new javax.swing.JTextField();
        Panel = new javax.swing.JPanel();
        b_inactivo = new javax.swing.JRadioButton();
        b_activo = new javax.swing.JRadioButton();
        btn_cerrar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText("Nombre :");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(26, 82, -1, -1));
        getContentPane().add(txt_nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(88, 79, 250, -1));

        jLabel2.setText("Apellido :");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(26, 116, -1, -1));
        getContentPane().add(txt_apellido, new org.netbeans.lib.awtextra.AbsoluteConstraints(88, 113, 250, -1));

        jLabel3.setText("DNI:");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(53, 153, -1, -1));
        getContentPane().add(txt_dni, new org.netbeans.lib.awtextra.AbsoluteConstraints(88, 150, 247, -1));

        jLabel4.setText("Usuario:");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(33, 184, -1, -1));

        txt_usuario.setEnabled(false);
        getContentPane().add(txt_usuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(88, 184, 247, -1));

        txt_clave.setEnabled(false);
        getContentPane().add(txt_clave, new org.netbeans.lib.awtextra.AbsoluteConstraints(88, 224, 247, -1));

        jLabel5.setText("Clave :");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(41, 227, -1, -1));

        jLabel6.setText("Correo:");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(37, 268, -1, -1));
        getContentPane().add(txt_correo, new org.netbeans.lib.awtextra.AbsoluteConstraints(88, 268, 247, -1));

        jLabel7.setText("Estado:");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(38, 304, -1, -1));

        CB_ROL.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Admin", "Vendedor", " " }));
        getContentPane().add(CB_ROL, new org.netbeans.lib.awtextra.AbsoluteConstraints(89, 29, -1, -1));

        jLabel8.setText("Rol:");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(57, 32, -1, -1));

        lb_Foto.setBackground(new java.awt.Color(204, 255, 255));
        lb_Foto.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        getContentPane().add(lb_Foto, new org.netbeans.lib.awtextra.AbsoluteConstraints(372, 98, 164, 164));

        btn_foto.setText("Foto");
        btn_foto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_fotoActionPerformed(evt);
            }
        });
        getContentPane().add(btn_foto, new org.netbeans.lib.awtextra.AbsoluteConstraints(413, 57, -1, -1));

        btn_agregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/BOTON AGREGAR.jpg"))); // NOI18N
        btn_agregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_agregarActionPerformed(evt);
            }
        });
        getContentPane().add(btn_agregar, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 400, 140, 40));

        btn_editar.setText("Editar");
        btn_editar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_editarActionPerformed(evt);
            }
        });
        getContentPane().add(btn_editar, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 360, -1, -1));

        btn_eliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/BOTON ELIMINAR.jpg"))); // NOI18N
        btn_eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_eliminarActionPerformed(evt);
            }
        });
        getContentPane().add(btn_eliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 400, 140, 40));

        btn_nuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/BOTONES.jpg"))); // NOI18N
        btn_nuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_nuevoActionPerformed(evt);
            }
        });
        getContentPane().add(btn_nuevo, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 350, 140, 40));

        btn_ListaUsuario.setText("Lista Usuario");
        btn_ListaUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ListaUsuarioActionPerformed(evt);
            }
        });
        getContentPane().add(btn_ListaUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 370, -1, -1));

        txt_foto.setEnabled(false);
        getContentPane().add(txt_foto, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 270, 160, -1));

        Panel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        b_inactivo.setText("Inactivo");

        b_activo.setText("Activo");
        b_activo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_activoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PanelLayout = new javax.swing.GroupLayout(Panel);
        Panel.setLayout(PanelLayout);
        PanelLayout.setHorizontalGroup(
            PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(b_activo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                .addComponent(b_inactivo)
                .addContainerGap())
        );
        PanelLayout.setVerticalGroup(
            PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelLayout.createSequentialGroup()
                .addContainerGap(11, Short.MAX_VALUE)
                .addGroup(PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(b_inactivo)
                    .addComponent(b_activo))
                .addContainerGap())
        );

        getContentPane().add(Panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 300, 160, 40));

        btn_cerrar.setText("CERRAR");
        btn_cerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cerrarActionPerformed(evt);
            }
        });
        getContentPane().add(btn_cerrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 460, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_agregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_agregarActionPerformed
        // TODO add your handling code here:
        if ("".equals(txt_nombre.getText())|| !"".equals(txt_apellido.getText())|| !"".equals(txt_dni.getText())|| !"".equals(txt_correo.getText())){
            pro.setNombre(txt_nombre.getText());
            pro.setApellido(txt_apellido.getText());
            pro.setDni(Integer.parseInt(txt_dni.getText()));
            pro.setCorreo(txt_correo.getText());
            //botones
           btg_estado.add(b_activo);
           btg_estado.add(b_inactivo);
           String estado = "Activo";
           if (b_activo.isSelected()) {
               estado = "Activo";
            } else {
               if (b_inactivo.isSelected()) {
                   estado = "Inactivo";
                           }
           }
           pro.setEstado(estado);
           //para el rol
           String rolSeleccionado = (String) CB_ROL.getSelectedItem();
           pro.setRol(rolSeleccionado);
           //para agregar la foto
           String rutaFoto = txt_foto.getText(); 
           File file = new File(rutaFoto); 

           if (file.exists()) {
               try (FileInputStream fis = new FileInputStream(file)) {
                   byte[] fotoBytes = new byte[(int) file.length()];
                   fis.read(fotoBytes);
                   pro.setFoto(fotoBytes);
            } catch (FileNotFoundException ex) {
                JOptionPane.showMessageDialog(this, "Archivo no encontrado");
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(this, "Error al leer el archivo de imagen");
                    }
               }
           proDao.AgregarCuenta(pro);
           JOptionPane.showMessageDialog(null, "CUENTA AGREGADO");
           //vincular con la tabla que esta en el otro Jframe
           ListaUsuario.getInstance().ListaUsuarios1();
           Limpiar();
           }else{
            JOptionPane.showMessageDialog(null, "ESTAN VACIOS,PORFAVOR COMPLETAR TODO LOS CASILLEROS");
            
        }
        
        
    }//GEN-LAST:event_btn_agregarActionPerformed

    private void btn_editarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_editarActionPerformed

        if ("".equals(txt_nombre.getText())) {
            JOptionPane.showMessageDialog(null, "Seleccione una fila");
        } else {
            pro.setNombre(txt_nombre.getText());
            pro.setApellido(txt_apellido.getText());
            pro.setDni(Integer.parseInt(txt_dni.getText()));
            pro.setCorreo(txt_correo.getText());
            //botones
           btg_estado.add(b_activo);
           btg_estado.add(b_inactivo);
           String estado = "Activo";
           if (b_activo.isSelected()) {
               estado = "Activo";
            } else {
               if (b_inactivo.isSelected()) {
                   estado = "Inactivo";
                           }
           }
           pro.setEstado(estado);
           //para el rol
           String rolSeleccionado = (String) CB_ROL.getSelectedItem();
           pro.setRol(rolSeleccionado);
           //para agregar la foto
           String rutaFoto = txt_foto.getText(); 
           File file = new File(rutaFoto); 

           if (file.exists()) {
               try (FileInputStream fis = new FileInputStream(file)) {
                   byte[] fotoBytes = new byte[(int) file.length()];
                   fis.read(fotoBytes);
                   pro.setFoto(fotoBytes);
            } catch (FileNotFoundException ex) {
                JOptionPane.showMessageDialog(this, "Archivo no encontrado");
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(this, "Error al leer el archivo de imagen");
                    }
               }
           proDao.EditarCuenta(pro);
           JOptionPane.showMessageDialog(null, "CUENTA EDITADA");
           //vincular con la tabla que esta en el otro Jframe
           ListaUsuario.getInstance().ListaUsuarios1();
           Limpiar();
            
            
        }
    }//GEN-LAST:event_btn_editarActionPerformed

    private void btn_ListaUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ListaUsuarioActionPerformed
        // TODO add your handling code here:
        ListaUsuario cuentaFrame = ListaUsuario.getInstance();
        
        if (!cuentaFrame.isVisible()) {
            cuentaFrame.setVisible(true);
            
        } else {
            cuentaFrame.setState(JFrame.NORMAL);
        }
        
        
    }//GEN-LAST:event_btn_ListaUsuarioActionPerformed

    private void b_activoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_activoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_b_activoActionPerformed

    private void btn_fotoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_fotoActionPerformed
        // TODO add your handling code here:
        FileNameExtensionFilter filtro=new FileNameExtensionFilter("Formatos de Archivos JPEG(*.JPG;.*JPEG)","jpg","jpeg");
        JFileChooser archivo=new JFileChooser();
        archivo.addChoosableFileFilter(filtro);
        archivo.setDialogTitle("Abrir Archivo");
        File ruta= new File("D:/");//para que aparesca la ruta
        archivo.setCurrentDirectory(ruta);
        
        int ventana =archivo.showOpenDialog(null);
        if (ventana== JFileChooser.APPROVE_OPTION) {
            File file=archivo.getSelectedFile();
            txt_foto.setText(String.valueOf(file));
            Image foto=getToolkit().createImage(txt_foto.getText());
            foto=foto.getScaledInstance(110, 110, Image.SCALE_DEFAULT);
            lb_Foto.setIcon(new ImageIcon(foto));
            
        }
    }//GEN-LAST:event_btn_fotoActionPerformed

    private void btn_eliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_eliminarActionPerformed
        
    }//GEN-LAST:event_btn_eliminarActionPerformed

    private void btn_nuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_nuevoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_nuevoActionPerformed

    private void btn_cerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cerrarActionPerformed
        // TODO add your handling code here:
        Login abrir =new Login();
        abrir.setVisible(true);
        this.dispose();
        
    }//GEN-LAST:event_btn_cerrarActionPerformed

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CrearCuenta().setVisible(true);
            }
        });
    }
    private void Limpiar(){
        txt_nombre.setText("");
        txt_apellido.setText("");
        txt_dni.setText("");
        String usuarioAleatorio = Generador.generarUsuario();
        String claveAleatoria = Generador.generarClave();
        pro.setUsuario(usuarioAleatorio);
        pro.setClave(claveAleatoria);
        txt_usuario.setText(usuarioAleatorio);
        txt_clave.setText(claveAleatoria);
        txt_correo.setText("");
        txt_foto.setText("");
        
    }
    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> CB_ROL;
    private javax.swing.JPanel Panel;
    private javax.swing.JRadioButton b_activo;
    private javax.swing.JRadioButton b_inactivo;
    private javax.swing.ButtonGroup btg_estado;
    private javax.swing.JButton btn_ListaUsuario;
    private javax.swing.JButton btn_agregar;
    private javax.swing.JButton btn_cerrar;
    private javax.swing.JButton btn_editar;
    private javax.swing.JButton btn_eliminar;
    private javax.swing.JButton btn_foto;
    private javax.swing.JButton btn_nuevo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel lb_Foto;
    private javax.swing.JTextField txt_apellido;
    private javax.swing.JTextField txt_clave;
    private javax.swing.JTextField txt_correo;
    private javax.swing.JTextField txt_dni;
    private javax.swing.JTextField txt_foto;
    private javax.swing.JTextField txt_nombre;
    private javax.swing.JTextField txt_usuario;
    // End of variables declaration//GEN-END:variables
}
