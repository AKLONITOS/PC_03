
package Clases;


public class Producto {
    int codigo;
    String nombre;
    String categoria;
    String talla;
    int stock;
    int stockmin;
    double preciocom;
    double preciovent;
    byte[] imagen;
    String color;
    boolean promocion;
    String fecha;
    String sexo;
    
    public Producto(){
        
    }

    public Producto(String nombre, double preciovent, byte[] imagen) {
        this.nombre = nombre;
        this.preciovent = preciovent;
        this.imagen = imagen;
    }

    public Producto(String nombre, String talla, double precio, byte[] imagen) {
        this.nombre = nombre;
        this.talla = talla;
        this.preciovent = precio;
        this.imagen = imagen;
    }
    
    

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getTalla() {
        return talla;
    }

    public void setTalla(String talla) {
        this.talla = talla;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getStockmin() {
        return stockmin;
    }

    public void setStockmin(int stockmin) {
        this.stockmin = stockmin;
    }

    public double getPreciocom() {
        return preciocom;
    }

    public void setPreciocom(double preciocom) {
        this.preciocom = preciocom;
    }

    public double getPreciovent() {
        return preciovent;
    }

    public void setPreciovent(double preciovent) {
        this.preciovent = preciovent;
    }

    public byte[] getImagen() {
        return imagen;
    }

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public boolean isPromocion() {
        return promocion;
    }

    public void setPromocion(boolean promocion) {
        this.promocion = promocion;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }
    

    
    
    

   
}
