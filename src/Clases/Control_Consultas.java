/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

import Conexiones.CerrarConexiones;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import Conexiones.Conexion;
import Interfaz.Consultas_Factura;
import static Interfaz.Consultas_Factura.jtb_consultasfac;
import Interfaz.Consultas_venta;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 * En esta clase se exponen todos los métodos para ejercer control sobre los
 * datos que van desde y hacia la tabla clientes. En esta clase se hace la
 * validación y organizacion de los datos.
 *
 * @author Sergio
 */


public class Control_Consultas {

   
    DefaultTableModel modelo;

    String info[][] = {};

    Conexion conectar = new Conexion();
    
    public void listarTodosFacturas() {
        
        String[] titulosColumnas = {"No_Facturas", "CLIENTE", "RAZÓN SOCIAL", "FECHA","TOTAL"};

        modelo = new DefaultTableModel(info, titulosColumnas) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
    
        Consultas_Factura.jtb_consultasfac.setModel(modelo);

        //ejecuta una consulta a la BD
        ejecutarConsultaTodaTabladeFacturas();

    }//cierra metodo listarTodosFacturas
    
        

     /**
     * Metodo para consultar todos los regsitros de la base de datos de clientes
     * y luego ser mostrados en una tabla.
     */
    Connection conexion = null;
    Statement sentencia = null;
    ResultSet resultado = null;
    PreparedStatement ps = null;

    public void ejecutarConsultaTodaTabladeFacturas() {

        try {
            conexion = Conexion.getConnection();

            sentencia = conexion.createStatement();
            String consultaSQL = "SELECT * FROM cabecera_venta c inner join cliente c on f.cliente=c.idCliente ORDER BY fecha ASC";
            resultado = sentencia.executeQuery(consultaSQL);


            //mientras haya datos en la BD ejecutar eso...
            while (resultado.next()) {


             int fact = resultado.getInt("No_Facturas");
                String cliente = resultado.getString("c.Nombre")+resultado.getString("c.Apellido");
                String fecha = resultado.getString("fecha");
                String total = resultado.getString("totals");



                //crea un vector donde los está la informacion (se crea una fila)
                Object[] info = {fact,cliente,fecha,total};

                //al modelo de la tabla le agrega una fila
                //con los datos que están en info
                modelo.addRow(info);

            }//cierra while (porque no hay mas datos en la BD)


        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"Error SQL:\n" + e);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,e);
            conexion = null;
        } finally {
            CerrarConexiones.metodoCerrarConexiones(conexion, sentencia, resultado, ps);
        }

    }//cierra metodo ejecutarConsulta
    
  
    public void buscarFacturas(String parametroBusqueda, boolean buscarPorFacturas, boolean buscarPorCliente, boolean buscarPorFecha) {

        String[] titulosColumnas = {"No_Facturas", "CLIENTE", "FECHA","TOTAL"};
        if ((parametroBusqueda.trim().length() == 0)) {
            JOptionPane.showMessageDialog(null,"Error, datos incorrectos");
        } else {

            modelo = new DefaultTableModel(info, titulosColumnas) {
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
            
            Consultas_Factura.jtb_consultasfac.setModel(modelo);
            
            buscarFacturasporBusqueda(parametroBusqueda, buscarPorFacturas, buscarPorCliente, buscarPorFecha);

        }

    }

    public void buscarFacturasporBusqueda(String parametroBusqueda, boolean buscarPorFacturas, boolean buscarPorCliente, boolean buscarPorFecha) {

        try {

            
            conexion = Conexion.getConnection();
            String selectSQL;
            resultado = null;
            if (buscarPorFacturas == true) {     
                System.out.print("buscando por facturas");
                selectSQL = "SELECT * FROM tb_facturas WHERE IdFactura LIKE ? ORDER BY fecha ASC";
                ps = conexion.prepareStatement(selectSQL);
                ps.setString(1, "%" + parametroBusqueda + "%");
            } 
            else if(buscarPorCliente== true){
                System.out.print("buscando por cliente");
                selectSQL = "SELECT * FROM tb_facturas f inner join cliente c on f.cliente=c.idCliente WHERE  c.Ruc_cliente LIKE ? or c.Nombre LIKE ? ORDER BY fecha ASC";
                ps = conexion.prepareStatement(selectSQL);

                 ps.setString(1, "%" + parametroBusqueda + "%");
                  ps.setString(2, "%" + parametroBusqueda + "%");
            }
            else if(buscarPorFecha== true){

                System.out.print("buscando por fecha -->"+parametroBusqueda);
                selectSQL = "SELECT * FROM tb_facturas f inner join  cliente c on f.IdCliente=c.idCliente WHERE f.fecha LIKE ?  ORDER BY fecha ASC";
                ps = conexion.prepareStatement(selectSQL);
                ps.setString(1, "%" + parametroBusqueda + "%");
            }
            resultado = ps.executeQuery();

            while (resultado.next()) {
                int fact = resultado.getInt("No_Facturas");
                String cliente = resultado.getString("c.Nombre_Cliente")+resultado.getString("c.Apellido_Cliente");
                String fecha = resultado.getString("fecha");
                String total = resultado.getString("totals");
                

                //crea un vector donde los está la informacion (se crea una fila)
                Object[] info = {fact,cliente, fecha,total};
                //al modelo de la tabla le agrega una fila
                //con los datos que están en info
                modelo.addRow(info);

            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Error\n Por la Causa" + e);
        } finally {
            CerrarConexiones.metodoCerrarConexiones(conexion, sentencia, resultado, ps);
        }


    }

    public void buscarFacturas ( String number){
        
        String[] titulosColumnas = {"ID-VENTAS", "PRODUCTOS", "CANTIDAD", "IMPORTE"};
        if( (number.trim().length()==0)){
            JOptionPane.showMessageDialog(null,"Error, Seleccione la Facturas");
        }
        else{
            
            modelo = new DefaultTableModel(info,titulosColumnas){
                public boolean isCellEditable(int row, int column)
                {
                return false;
                }
                
            };

       Consultas_Factura.jtb_consultasfac.setModel(modelo); //duda jtb
       
        int[] anchos = {80, 200,50,145};
        for(int i = 0; i < jtb_consultasfac.getColumnCount(); i++) { //duda jtb
        jtb_consultasfac.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);
        }

        buscarFacturasDetall(number);         
        }
        
    }
      public void buscarFacturasDetall(String number) {

        try {
            conexion = Conexion.getConnection();
            String selectSQL;
            resultado = null;
           
                selectSQL = "SELECT * FROM table_ventas WHERE IdFactura LIKE ? ORDER BY IdVentas ASC";
                ps = conexion.prepareStatement(selectSQL);
                ps.setString(1, "%" + number + "%");
            
            resultado = ps.executeQuery();

            while (resultado.next()) {
                String id = resultado.getString("idVentas");
                String product = resultado.getString("productos");
                String cant = resultado.getString("cantidad");
                String imp = resultado.getString("importe");

                String name="";
                Statement comando = conexion.createStatement();
                 ResultSet registro = comando.executeQuery("select Codigo,Nombre from productos where Codigo=" +product);
            
                 if (registro.next() == true) {
                     name = registro.getString("Nombre");
                    }

                Object[] info = {id,name,cant,imp};

                modelo.addRow(info);

            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Error\n " + e);
        } finally {
            CerrarConexiones.metodoCerrarConexiones(conexion, sentencia, resultado, ps);
        }


    }

    
    public void listarTodosVentas() {
        
        String[] titulosColumnas = {"id-Ventas","Fecha", "Producto", "cantidad", "Importe"};

        modelo = new DefaultTableModel(info, titulosColumnas) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        Consultas_venta.tb_consultasven.setModel(modelo);

        ejecutarConsultaTodaTabladeVentas();

    }

    public void ejecutarConsultaTodaTabladeVentas() {

        try {
            conexion = Conexion.getConnection();

            sentencia = conexion.createStatement();
            String consultaSQL = "SELECT * FROM tb_facturas f inner join  tb_ventas v on f.IdFactura=v.IdFactura inner join  productos p on v.Productos=p.Codigo ORDER BY v.IdVentas ASC";
            resultado = sentencia.executeQuery(consultaSQL);

            while (resultado.next()) {


                int num = resultado.getInt("idVentas");
                String fact = resultado.getString("f.Fecha");
                String prod = resultado.getString("p.Nombre");
                String cant = resultado.getString("cantidad");
                String importe = resultado.getString("importe");

                Object[] info = {num,fact,prod,cant,importe};

                modelo.addRow(info);

            }


        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"Error SQL:\n" + e);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,e);
            conexion = null;
        } finally {
            CerrarConexiones.metodoCerrarConexiones(conexion, sentencia, resultado, ps);
        }

    }
      
    
            
                public void buscarFacturasdeVentasFecha(String parametroBusqueda,String parametroBusqued2) {

        String[] titulosColumnas = {"id-Ventas","Fecha", "Producto", "cantidad", "Importe"};
        if ((parametroBusqueda.trim().length() == 0)) {
            JOptionPane.showMessageDialog(null,"Error, datos incorrectos");
        } else {

            modelo = new DefaultTableModel(info, titulosColumnas) {
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
            
            
            
            Consultas_venta.tb_consultasven.setModel(modelo);
            
            buscarFacturasporBusquedaenVentasFecha(parametroBusqueda,parametroBusqued2);

        }

    }//cierra metodo buscarCliente
    public void buscarFacturasdeVentas(String parametroBusqueda) {

        String[] titulosColumnas = {"id-Ventas","Fecha", "Producto", "cantidad", "Importe"};
        if ((parametroBusqueda.trim().length() == 0)) {
            JOptionPane.showMessageDialog(null,"Error, datos incorrectos");
        } else {

            modelo = new DefaultTableModel(info, titulosColumnas) {
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
            


            
            Consultas_venta.tb_consultasven.setModel(modelo);
            
            buscarFacturasporBusquedaenVentas(parametroBusqueda);

        }

    }

    public void buscarFacturasporBusquedaenVentasFecha(String parametroBusqueda,String parametroBusqueda2) {

        try {

        
            conexion = Conexion.getConnection();
            String selectSQL;
            resultado = null;
                System.err.println(parametroBusqueda2+"    "+parametroBusqueda);
                System.out.print("buscando por facturas en Ventas");
               selectSQL = "SELECT * FROM tb_facturas f inner join tb_ventas v on f.IdFactura=v.IdFactura inner join  productos p on v.Productos=p.Codigo WHERE " +"  fecha<= '" +parametroBusqueda2+"' AND  fecha>= '"+parametroBusqueda+"'";
               
                      Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery(selectSQL);
            
             
float total=0;

double stock=0;

            while (rs.next()) {
                int num = rs.getInt("IdVentas");
                String fact = rs.getString("f.Fecha");
                String prod = rs.getString("p.Nombre");
                String cant = rs.getString("v.Cantidad");
                String importe = rs.getString("v.Importe");
                total=total+rs.getFloat("v.Importe");
                
                stock=stock+(rs.getInt("p.Stock"))-(rs.getInt("v.Cantidad"));

                Object[] info = {num,fact,prod,cant,importe};
                
                modelo.addRow(info);

            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Error\n Por la Causa" + e);
        } finally {
            CerrarConexiones.metodoCerrarConexiones(conexion, sentencia, resultado, ps);
        }


    }
    
    public void buscarFacturasporBusquedaenVentas(String parametroBusqueda) {

        try {

        
            conexion = Conexion.getConnection();
            String selectSQL;
            resultado = null;
                
                System.out.print("buscando por facturas en Ventas");
               selectSQL = "SELECT * FROM table_Facturas f inner join  table_Ventas v on f.No_Facturas=v.No_Facturas inner join  table_Productos p on v.Productos=p.idProductos WHERE p.nombreProductos LIKE ? ORDER BY v.idVentas ASC";
                ps = conexion.prepareStatement(selectSQL);
                ps.setString(1, "%" + parametroBusqueda + "%");
            
            resultado = ps.executeQuery();
float total=0;


            while (resultado.next()) {
                int num = resultado.getInt("IdVenta");
                String fact = resultado.getString("f.Fecha");
                String prod = resultado.getString("p.Nombre");
                String cant = resultado.getString("v.Cantidad");
                String importe = resultado.getString("v.Importe");
                
total=total+resultado.getInt("v.Importe");

                
                Object[] info = {num,fact,prod,cant,importe};
                
                modelo.addRow(info);

            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Error\n Por la Causa" + e);
        } finally {
            CerrarConexiones.metodoCerrarConexiones(conexion, sentencia, resultado, ps);
        }


    }
      
      
}

