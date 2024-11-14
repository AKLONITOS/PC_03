
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
               
               Listapro.add(pro);
               }
            }catch (Exception e) {
                System.out.println(e.toString());
        }
        return Listapro;
    }
    public boolean AgregarProductos(Producto pro){
        String sql="INSERT INTO productos (Codigo , Nombre , Categoria , Talla , Stock , Stockmin,Precioco,Preciovent,Imagen , Color , Promocion ,Fecha) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
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
            ps.execute();
            return true;
        }catch(SQLException e){
            System.out.println(e.toString());
            return false;
        }
        
    }
    private boolean esFechaValida(String fechaCaducidadStr) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            Date fechaCaducidad = sdf.parse(fechaCaducidadStr);
            Date fechaActual = new Date();

           
            return fechaCaducidad.after(fechaActual);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public List<byte[]> obtenerImagenesParaBanner() {
        List<byte[]> bannerImages = new ArrayList<>();
        String sql = "SELECT Imagen, Promocion, Fecha FROM productos";

        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                boolean promocion = rs.getBoolean("Promocion");
                String fechaCaducidadStr = rs.getString("Fecha");
                byte[] imgBytes = rs.getBytes("Imagen");

                
                if (promocion && esFechaValida(fechaCaducidadStr)) {
                    bannerImages.add(imgBytes);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return bannerImages;
    }
    
}
