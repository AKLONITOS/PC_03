
package FuncionamientoTablas;

import Clases.Cuenta;
import Conexiones.Conexion;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class TablaCuenta {
    Connection con;
    Conexion cn=new Conexion();
    PreparedStatement ps;
    ResultSet rs;
    FileInputStream fis;
    
    public boolean AgregarCuenta(Cuenta CC) {
        
    String sql = "INSERT INTO cuentas (Nombre , Apellido , DNI , Usuario , Clave , Correo , Estado , Rol , Foto) VALUES (?,?,?,?,?,?,?,?,?)";
    try {
        FileInputStream archivofoto;
        con = cn.getConnection();
        ps = con.prepareStatement(sql);
        ps.setString(1, CC.getNombre());
        ps.setString(2, CC.getApellido());
        ps.setInt(3, CC.getDNI());
        ps.setString(4, CC.getUsuario());
        ps.setString(5, CC.getClave());
        ps.setString(6, CC.getCorreo());
        ps.setString(7, CC.getEstado());
        ps.setString(8, CC.getROL());
        ps.setBytes(9, CC.getFoto());
        ps.execute();
        return true;
    } catch (SQLException e) {
        System.out.println(e.toString());
        return false;
    }
    
}
}
