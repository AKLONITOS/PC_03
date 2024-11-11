package Interfaz;

import Clases.Cuenta;
import FuncionamientoTablas.TablaCuenta;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

public class ListaUsuario extends javax.swing.JFrame {
    private static ListaUsuario instance = null;
    CrearCuenta pro=new CrearCuenta();
    TablaCuenta proDao=new TablaCuenta();
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 517, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(217, 217, 217)
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 438, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton1)
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
        if (pro == null) {
            pro = new CrearCuenta();
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

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ListaUsuario().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tb_ListaUsuario;
    // End of variables declaration//GEN-END:variables
}
