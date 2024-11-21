/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package FuncionamientoTablas;

import Clases.Cliente;
import Conexiones.Conexion;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author EQUIPO
 */
public class TablaCliente {
    Connection con;
    Conexion cn=new Conexion();
    PreparedStatement ps;
    ResultSet rs;
    FileInputStream fis;
    
    public List ListadeClientes(){
        List<Cliente> Listacli = new ArrayList();
        String sql="SELECT * FROM cliente";
        
        try {
           con = cn.getConnection();
           ps = con.prepareStatement(sql);
           rs = ps.executeQuery();
           while (rs.next()) {               
               Cliente pro = new Cliente();
               pro.setIdCliente(rs.getString("idCliente"));
               pro.setNombre(rs.getString("Nombre"));
               pro.setApellido(rs.getString("Apellido"));
               pro.setDNI(rs.getInt("DNI"));
               pro.setTelefono(rs.getString("telefono"));
               pro.setDireccion(rs.getString("direccion"));
               pro.setEstado(rs.getString("estado"));
               pro.setRuc_cliente(rs.getString("Ruc_cliente"));
               
               
               Listacli.add(pro);
               }
            }catch (Exception e) {
                System.out.println(e.toString());
        }
        return Listacli;
    }
    public boolean AgregarCliente(Cliente cli){
        String sql="INSERT INTO cliente (idCliente, Nombre, Apellido, DNI, telefono, direccion, estado, Ruc_cliente) VALUES (?,?,?,?,?,?,?,?)";
        try{
            
            ps=con.prepareStatement(sql);
            ps.setString(1, cli.getIdCliente());
            ps.setString(2, cli.getNombre());
            ps.setString(3, cli.getApellido());
            ps.setInt(4, cli.getDNI());
            ps.setString(5, cli.getTelefono());
            ps.setString(6, cli.getDireccion());
            ps.setString(7, cli.getEstado());
            ps.setString(8, cli.getRuc_cliente());
            ps.execute();
            return true;
        }catch(SQLException e){
            System.out.println(e.toString());
            return false;
        }
        
    }
    
    public boolean ElimarProducto(String cod){
        String sql="DELETE FROM cliente WHERE idCliente = ?";
        try{
            ps=con.prepareStatement(sql);
            ps.setString(1,cod);
            ps.execute();
            return true;
        }catch (SQLException e){
            System.out.println(e.toString());
            return false;
        }finally{
            try{
                con.close();
            }catch(SQLException ex){
                System.out.println(ex.toString());
            }
        }
        
    }
    
    
    
}
