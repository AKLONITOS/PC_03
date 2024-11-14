
package FuncionamientoTablas;

import Clases.Cuenta;
import Conexiones.Conexion;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;


public class TablaCuenta {
    Connection con;
    Conexion cn=new Conexion();
    PreparedStatement ps;
    ResultSet rs;
    FileInputStream fis;
    
    public boolean AgregarCuenta(Cuenta CC) {
    //Agregar cuenta    
    String sql = "INSERT INTO cuentas (Nombre , Apellido , DNI , Usuario , Clave , Correo , Estado , Rol , Foto) VALUES (?,?,?,?,?,?,?,?,?)";
    try {
        
        con = cn.getConnection();
        ps = con.prepareStatement(sql);
        ps.setString(1, CC.getNombre());
        ps.setString(2, CC.getApellido());
        ps.setInt(3, CC.getDni());
        ps.setString(4, CC.getUsuario());
        ps.setString(5, CC.getClave());
        ps.setString(6, CC.getCorreo());
        ps.setString(7, CC.getEstado());
        ps.setString(8, CC.getRol());
        ps.setBytes(9, CC.getFoto());
        ps.execute();
        return true;
    } catch (SQLException e) {
        System.out.println(e.toString());
        return false;
        }
    }
    //Agregar los datos de la base de datos
    public List ListadeCuentas(){
        List<Cuenta> Listacu = new ArrayList();
        String sql="SELECT * FROM cuentas";
        
        try {
           con = cn.getConnection();
           ps = con.prepareStatement(sql);
           rs = ps.executeQuery();
           while (rs.next()) {               
               Cuenta Cu = new Cuenta();
               Cu.setNombre(rs.getString("Nombre"));
               Cu.setApellido(rs.getString("Apellido"));
               Cu.setDni(rs.getInt("DNI"));
               Cu.setUsuario(rs.getString("Usuario"));
               Cu.setClave(rs.getString("Clave"));
               Cu.setCorreo(rs.getString("Correo"));
               Cu.setEstado(rs.getString("Estado"));
               Cu.setRol(rs.getString("Rol"));
               Cu.setFoto(rs.getBytes("Foto"));
               Listacu.add(Cu);
               }
            }catch (Exception e) {
                System.out.println(e.toString());
        }
        return Listacu;
    }
    
  //Elinar en la base de datos
    public boolean ElimarCuenta(String nom){
        String sql="DELETE FROM cuentas WHERE Nombre = ?";
        try{
            
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, nom);
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
    public boolean EditarCuenta(Cuenta cuenta){
       String sql = "UPDATE cuentas SET Nombre = ?, Apellido = ?, DNI = ?, Usuario = ?, Clave = ?, Correo = ?, Estado = ?, Rol = ?, Foto = ? WHERE DNI = ?";
       try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, cuenta.getNombre());
            ps.setString(2, cuenta.getApellido());
            ps.setInt(3, cuenta.getDni());
            ps.setString(4, cuenta.getUsuario());
            ps.setString(5, cuenta.getClave());
            ps.setString(6, cuenta.getCorreo());
            ps.setString(7, cuenta.getEstado());
            ps.setString(8, cuenta.getRol());
            if (cuenta.getFoto() == null) {
                ps.setBytes(9, new byte[0]);
            } else {
                ps.setBytes(9, cuenta.getFoto());
            }
            ps.setInt(10, cuenta.getDni());
            ps.executeUpdate();
            return true;
            } catch (SQLException e) {
            System.err.println("Error al editar la cuenta: " + e.getMessage());
            System.out.println(e.toString());
            return false;
            }finally{
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println(e.toString());
            }
        }
    }
    
    public List<Cuenta> listaDeUsuariosYClaves() {
    List<Cuenta> listaCuentas = new ArrayList<>();
    String sql = "SELECT Usuario, Clave FROM cuentas";
    
    try (Connection con = cn.getConnection();
         PreparedStatement ps = con.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {

        while (rs.next()) {
            Cuenta cuenta = new Cuenta();
            cuenta.setUsuario(rs.getString("Usuario"));
            cuenta.setClave(rs.getString("Clave"));
            listaCuentas.add(cuenta);
        }
    } catch (Exception e) {
        System.out.println("Error: " + e.toString());
    }
    
    return listaCuentas;
    }
    
    public List<Cuenta> obtenerUsuariosYRoles() {
        List<Cuenta> listaCuentas = new ArrayList<>();
        String sql = "SELECT Usuario, Rol FROM cuentas";
        
        try (Connection con = cn.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Cuenta cuenta = new Cuenta();
                cuenta.setUsuario(rs.getString("Usuario"));
                cuenta.setRol(rs.getString("Rol"));
                listaCuentas.add(cuenta);
            }
        } catch (Exception e) {
            System.out.println("Error al obtener usuarios y roles: " + e.getMessage());
        }
        
        return listaCuentas;
    }
    
    
}
