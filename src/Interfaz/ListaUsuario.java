package Interfaz;

import Clases.Cuenta;
import FuncionamientoTablas.TablaCuenta;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import javax.swing.table.DefaultTableModel;

public class ListaUsuario extends javax.swing.JFrame {
    private static ListaUsuario instance = null;
    CrearCuenta pro=new CrearCuenta();
    TablaCuenta proDao=new TablaCuenta();
    Cuenta CU=new Cuenta();
    DefaultTableModel modelo = new DefaultTableModel();
    
    

    public ListaUsuario() {
        initComponents();
        ListaUsuarios1();
        
        int x = pro.getX() + pro.getWidth(); 
        int y = pro.getY(); //

        this.setLocation(x, y);
        JPanel panelForm = new JPanel();
        
        


        
      
    }
    public static ListaUsuario getInstance() {
        if (instance == null) {
            instance = new ListaUsuario();
        }
        return instance;
    }
    public void ListaUsuarios1() {
        LimpiarTabla();
        List<Cuenta> Listapro = proDao.ListadeCuentas();
        modelo = (DefaultTableModel) tb_ListaUsuario.getModel();
        Object[] ob = new Object[9];
        for (int i = 0; i < Listapro.size(); i++) {
            ob[0] = Listapro.get(i).getNombre();
            ob[1] = Listapro.get(i).getApellido();
            ob[2] = Listapro.get(i).getDni();
            ob[3] = Listapro.get(i).getUsuario();
            ob[4] = Listapro.get(i).getClave();
            ob[5] = Listapro.get(i).getCorreo();
            ob[6] = Listapro.get(i).getEstado();
            ob[7] = Listapro.get(i).getRol();
            ob[8] = Listapro.get(i).getFoto();
            
            tb_ListaUsuario.getColumnModel().getColumn(8).setMaxWidth(0);
            tb_ListaUsuario.getColumnModel().getColumn(8).setMinWidth(0);
            tb_ListaUsuario.getColumnModel().getColumn(8).setPreferredWidth(0);  // Ocultar columna de foto (índice 8)
            tb_ListaUsuario.getTableHeader().getColumnModel().getColumn(8).setMaxWidth(0);
            modelo.addRow(ob);
            
        }
        tb_ListaUsuario.setModel(modelo);
        

    }
    public void LimpiarTabla(){
        for (int i = 0; i < tb_ListaUsuario.getRowCount(); i++) {
            modelo.removeRow(i);
            i=i-1;
            
        }
    }




    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tb_ListaUsuario = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        btn_Eliminar = new javax.swing.JButton();
        txt_nombre = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tb_ListaUsuario.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nombre", "Apellido", "DNI", "Usuario", "Clave", "Correo", "Estado", "Rol", "Foto"
            }
        ));
        tb_ListaUsuario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb_ListaUsuarioMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tb_ListaUsuario);

        jButton1.setText("Cerrar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        btn_Eliminar.setText("Eliminar");
        btn_Eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_EliminarActionPerformed(evt);
            }
        });

        txt_nombre.setEnabled(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 523, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(130, 130, 130)
                        .addComponent(btn_Eliminar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton1)
                        .addGap(216, 216, 216)
                        .addComponent(txt_nombre, javax.swing.GroupLayout.PREFERRED_SIZE, 4, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 438, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(btn_Eliminar)
                    .addComponent(txt_nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tb_ListaUsuarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_ListaUsuarioMouseClicked
        
        int fila = tb_ListaUsuario.rowAtPoint(evt.getPoint());

   
        String nombre = tb_ListaUsuario.getValueAt(fila, 0).toString();
        String apellido = tb_ListaUsuario.getValueAt(fila, 1).toString();
        String dni = tb_ListaUsuario.getValueAt(fila, 2).toString();
        String usuario = tb_ListaUsuario.getValueAt(fila, 3).toString();
        String clave = tb_ListaUsuario.getValueAt(fila, 4).toString();
        String correo = tb_ListaUsuario.getValueAt(fila, 5).toString();
        String estado = tb_ListaUsuario.getValueAt(fila, 6).toString();
        String rol = tb_ListaUsuario.getValueAt(fila, 7).toString();
        byte[] fotoBytes = (byte[]) tb_ListaUsuario.getValueAt(fila, 8);
        txt_nombre.setText(nombre);
        if (pro == null) {
            pro = new CrearCuenta();
        }
        if (fila >= 0) {

        if (!pro.isVisible()) {
            pro.setVisible(true);
            
        }
        int selectedRow = tb_ListaUsuario.getSelectedRow();
    
        }

        pro.llenarCampos(nombre, apellido, dni, usuario, clave, correo, estado, rol, fotoBytes);
        
         if (!pro.isVisible()) {
            pro.setVisible(true);
         }
    }//GEN-LAST:event_tb_ListaUsuarioMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        this.dispose();
        
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btn_EliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_EliminarActionPerformed
        // TODO add your handling code here:
       if(!"".equals(txt_nombre.getText())){
            int pregunta=JOptionPane.showConfirmDialog(null, "ESTA SEGURO QUE QUIERES ELIMINAR");
            if(pregunta==0){
                
                proDao.ElimarCuenta(txt_nombre.getText());
                ListaUsuarios1();
                
                
            }
        }
    }//GEN-LAST:event_btn_EliminarActionPerformed

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ListaUsuario().setVisible(true);
            }
        });
    }
    
        
    
    


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_Eliminar;
    private javax.swing.JButton jButton1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tb_ListaUsuario;
    private javax.swing.JTextField txt_nombre;
    // End of variables declaration//GEN-END:variables
}
