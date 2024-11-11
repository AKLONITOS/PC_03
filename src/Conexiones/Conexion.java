/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Conexiones;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;


public class Conexion {
    public class conexion {
    private static String DRIVER="com.mysql.cj.jdbc.Driver";
    private static String USUARIO="root";
    private static String PASSWORD="";
    private static String URL="jdbc:mysql://localhost:3306/crearcuenta";
    
    static{
        try{
            Class.forName(DRIVER);
        }catch(ClassNotFoundException e){
            JOptionPane.showMessageDialog(null, "ERROR EN EL DRIVER: "+e);
            
        }
    }
    public Connection getConnection(){
        Connection con=null;
        try{
            con=DriverManager.getConnection(URL,USUARIO,PASSWORD);
            //JOptionPane.showMessageDialog(null,"Conexion Exitosa");
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Error en la Conexion"+e);
        }
        return con;
    }
    }
    
}
