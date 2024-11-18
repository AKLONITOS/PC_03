
package FuncionamientoTablas;

import Clases.Producto;
import Conexiones.Conexion;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class TablaProductos {
    Connection con;
    Conexion cn=new Conexion();
    PreparedStatement ps;
    ResultSet rs;
    FileInputStream fis;
    
    public List ListadeProductos(){
        List<Producto> Listapro = new ArrayList();
        String sql="SELECT * FROM productos";
        
        try {
           con = cn.getConnection();
           ps = con.prepareStatement(sql);
           rs = ps.executeQuery();
           while (rs.next()) {               
               Producto pro = new Producto();
               pro.setCodigo(rs.getString("Codigo"));
               pro.setNombre(rs.getString("Nombre"));
               pro.setCategoria(rs.getString("Categoria"));
               pro.setTalla(rs.getString("Talla"));
               pro.setStock(rs.getInt("Stock"));
               pro.setStockmin(rs.getInt("Stockmin"));
               pro.setPreciocom(rs.getDouble("Precioco"));
               pro.setPreciovent(rs.getDouble("Preciovent"));
               pro.setImagen(rs.getBytes("Imagen"));
               pro.setColor(rs.getString("Color"));
               pro.setPromocion(rs.getBoolean("Promocion"));
               pro.setFecha(rs.getString("Fecha"));
               pro.setSexo(rs.getString("Sexo"));
               
               Listapro.add(pro);
               }
            }catch (Exception e) {
                System.out.println(e.toString());
        }
        return Listapro;
    }
    public boolean AgregarProductos(Producto pro){
        String sql="INSERT INTO productos (Codigo , Nombre , Categoria , Talla , Stock , Stockmin,Precioco,Preciovent,Imagen , Color , Promocion ,Fecha,Sexo) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try{
            con=cn.getConnection();
            ps=con.prepareStatement(sql);
            ps.setString(1, pro.getCodigo());
            ps.setString(2, pro.getNombre());
            ps.setString(3, pro.getCategoria());
            ps.setString(4, pro.getTalla());
            ps.setInt(5, pro.getStock());
            ps.setInt(6, pro.getStockmin());
            ps.setDouble(7, pro.getPreciocom());
            ps.setDouble(8, pro.getPreciovent());
            ps.setBytes(9, pro.getImagen());
            ps.setString(10, pro.getColor());
            ps.setBoolean(11, pro.isPromocion());
            ps.setString(12, pro.getFecha());
            ps.setString(13, pro.getSexo());
            ps.execute();
            return true;
        }catch(SQLException e){
            System.out.println(e.toString());
            return false;
        }
        
    }
    
    public boolean ElimarProducto(String cod){
        String sql="DELETE FROM productos WHERE codigo = ?";
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
    public List<Producto> cargarProductos() {
    List<Producto> productos = new ArrayList<>();
    
    String sql = "SELECT Nombre, Preciovent, Imagen, Fecha FROM productos WHERE Promocion = 1 AND Fecha >= CURDATE()";

    try {
        con = cn.getConnection();
        ps = con.prepareStatement(sql);
        rs = ps.executeQuery();

        while (rs.next()) {
            String nombre = rs.getString("Nombre");
            double precio = rs.getDouble("Preciovent");
            byte[] imagen = rs.getBytes("Imagen");
            Date fecha = rs.getDate("Fecha"); 

            
            if (fecha != null && fecha.after(new Date())) {
                if (imagen != null && imagen.length > 0) {
                    Producto producto = new Producto(nombre, precio, imagen);
                    productos.add(producto);
                }
            }
        }
        System.out.println("Número de productos cargados: " + productos.size());
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        try {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            if (con != null) con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    return productos;
    }
    
    public List<Producto> listarProductosPorCategoria(String categoria) {
    List<Producto> productos = new ArrayList<>();
    String sql = "SELECT * FROM productos WHERE Categoria = ?";
    try {
        con = cn.getConnection();
        ps = con.prepareStatement(sql);
        ps.setString(1, categoria);
        rs = ps.executeQuery();

        while (rs.next()) {
            Producto producto = new Producto();
            producto.setNombre(rs.getString("Nombre"));
            producto.setPreciovent(rs.getDouble("Preciovent"));
            producto.setImagen(rs.getBytes("Imagen"));
            productos.add(producto);
        }
    } catch (Exception e) {
        e.printStackTrace();
        System.out.println("Error al listar productos.");
    }
    return productos;
    }
}
       
    

    
  

    
    
    
    

