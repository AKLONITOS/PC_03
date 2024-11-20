/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Conexiones;

/**
 *
 * @author EQUIPO
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;


public class CerrarConexiones {
    
    
    
    public static void metodoCerrarConexiones(Connection conexion, Statement sentencia, ResultSet resultado, PreparedStatement ps ){
        
        if(ps != null){
            try{
                ps.close();
            }
            catch(SQLException e1){
                JOptionPane.showMessageDialog(null,"No se pudo cerrar el PreparedStatement(ps)\n Error: "+e1);
                
            }
            resultado = null;
        }
        //- - - - - - - - - - - - - - - - - - - - - -
        if(resultado != null){
            try{
                resultado.close();
            }
            catch(SQLException e1){
                JOptionPane.showMessageDialog(null,"No se pudo cerrar el ResultSet (resultado)\n Error: "+e1);
            }
            resultado = null;
        }
        
        if(sentencia != null){
            try{
                sentencia.close();
            }
            catch(SQLException e1){
                JOptionPane.showMessageDialog(null,"No se pudo cerrar el Statement (sentencia)\n Error "+e1);
            }
            sentencia = null;
        }

        if(conexion != null){
            try{
                conexion.close();
            }
            catch(SQLException e1){
                JOptionPane.showMessageDialog(null,"No se pudo cerrar Connection (conexion)\n Error "+e1);
            }
            conexion = null;
        }
        
    }
  
}
