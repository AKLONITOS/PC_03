
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
        FileInputStream archivofoto;
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
    
    
    

}
