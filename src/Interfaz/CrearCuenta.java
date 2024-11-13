
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
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;



public class CrearCuenta extends javax.swing.JFrame {
    Cuenta pro=new Cuenta();
    TablaCuenta proDao=new TablaCuenta();
    private String nombreUsuario;
    

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

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Nombre :");

        jLabel2.setText("Apellido :");

        jLabel3.setText("DNI:");

        jLabel4.setText("Usuario:");

        txt_usuario.setEnabled(false);

        txt_clave.setEnabled(false);

        jLabel5.setText("Clave :");

        jLabel6.setText("Correo:");

        jLabel7.setText("Estado:");

        CB_ROL.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Admin", "Vendedor", " " }));

        jLabel8.setText("Rol:");

        lb_Foto.setBackground(new java.awt.Color(204, 255, 255));
        lb_Foto.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        btn_foto.setText("Foto");
        btn_foto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_fotoActionPerformed(evt);
            }
        });

        btn_agregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/BOTON AGREGAR.jpg"))); // NOI18N
        btn_agregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_agregarActionPerformed(evt);
            }
        });

        btn_editar.setText("Editar");
        btn_editar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_editarActionPerformed(evt);
            }
        });

        btn_eliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/BOTON ELIMINAR.jpg"))); // NOI18N
        btn_eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_eliminarActionPerformed(evt);
            }
        });

        btn_nuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/BOTONES.jpg"))); // NOI18N

        btn_ListaUsuario.setText("Lista Usuario");
        btn_ListaUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ListaUsuarioActionPerformed(evt);
            }
        });

        txt_foto.setEnabled(false);

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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(57, 57, 57)
                .addComponent(jLabel8)
                .addGap(12, 12, 12)
                .addComponent(CB_ROL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(jLabel3))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(jLabel4))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(jLabel5)))
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_nombre, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_apellido, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_dni, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_usuario, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_clave, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addComponent(btn_foto))
                    .addComponent(lb_Foto, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGroup(layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(jLabel6)
                .addGap(12, 12, 12)
                .addComponent(txt_correo, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(txt_foto, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(jLabel7)
                .addGap(24, 24, 24)
                .addComponent(Panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(btn_nuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(btn_editar)
                .addGap(138, 138, 138)
                .addComponent(btn_ListaUsuario))
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(btn_agregar, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(btn_eliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel8))
                    .addComponent(CB_ROL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel2)
                        .addGap(21, 21, 21)
                        .addComponent(jLabel3)
                        .addGap(15, 15, 15)
                        .addComponent(jLabel4)
                        .addGap(27, 27, 27)
                        .addComponent(jLabel5))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(txt_nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(txt_apellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(15, 15, 15)
                        .addComponent(txt_dni, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(txt_usuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txt_clave, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btn_foto)
                        .addGap(18, 18, 18)
                        .addComponent(lb_Foto, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(txt_correo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(txt_foto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(8, 8, 8)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jLabel7))
                    .addComponent(Panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_nuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(btn_editar))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(btn_ListaUsuario)))
                .addGap(7, 7, 7)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_agregar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_eliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

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
