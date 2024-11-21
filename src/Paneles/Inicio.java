
package Paneles;

import Clases.Producto;
import FuncionamientoTablas.TablaProductos;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;



public class Inicio extends javax.swing.JPanel {
    Producto pro =new Producto();
    TablaProductos proDao = new TablaProductos();
    List<Producto> productos =proDao.ListadeProductos();
    private List<JLabel> puntos = new ArrayList<>();
    
    private int index = 0;
            
    
    
    
    
    
    public Inicio() {
        initComponents();
        //ocultar pestañas

        PanelTallas.setLayout(new FlowLayout(FlowLayout.CENTER));


        Pestañas.setBounds(10, 10, 360, 200);

       
        Pestañas.setUI(new javax.swing.plaf.basic.BasicTabbedPaneUI() {
            @Override
            protected void paintTabArea(Graphics g, int tabPlacement, int selectedIndex) {
                 g.setColor(Color.CYAN);

                
                int tabAreaWidth = Pestañas.getWidth();
                int tabAreaHeight = 20;
               
                g.fillRect(0, 0, tabAreaWidth, tabAreaHeight);
               
            }

            @Override
            protected void paintTabBackground(Graphics g, int tabPlacement, int index, int x, int y, int width, int height, boolean isSelected) {
                
            }
        });
        
        
        System.out.println(productos);
        
        proDao.cargarProductos();
        crearPuntos();
         
        if (!productos.isEmpty()) {
            actualizarImagen();
            System.out.println("esta qui");
        } else {
            JOptionPane.showMessageDialog(this, "No se encontraron imágenes en la base de datos", "Error", JOptionPane.ERROR_MESSAGE);
        }

        // Listeners para los botones
        btn_atras.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cambiarImagen(-1);
            }
        });

        btn_siguiente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cambiarImagen(1);
            }
        });

        // tiempo de cambio
        Timer timer = new Timer(10000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cambiarImagen(1); // Cambiar a la siguiente imagen automáticamente
            }
        });

        // Iniciar tiempo
        timer.start();

    }
    private void crearPuntos() {
        // Crear un panel para los puntos
        
        puntitos.setLayout(new FlowLayout(FlowLayout.CENTER)); 
        puntitos.setOpaque(false); // panel de puntos sea transparente
        
        // Crea un punto por cada producto
        for (int i = 0; i < productos.size(); i++) {
            final int indice = i;
            JLabel punto = new JLabel("•", SwingConstants.CENTER); 
            punto.setFont(new Font("Arial", Font.PLAIN, 30)); 
            punto.setForeground(i == 0 ? Color.RED : Color.GRAY); 
            punto.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    index = indice; 
                    actualizarImagen(); 
                    actualizarPuntos();
                }
            });
            puntos.add(punto); 
           puntitos.add(punto); 
        }

        
        this.add(puntitos, BorderLayout.SOUTH); // Colocar los puntos asi abajo
    }
    
    private void cambiarImagen(int direccion) {
        
        if (productos.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay productos disponibles", "Error", JOptionPane.ERROR_MESSAGE);
            return;  
        }

        index = (index + direccion + productos.size()) % productos.size();
        actualizarImagen();
        actualizarPuntos();
    }
    
    private void actualizarImagen() {
        try {
            
            
            Producto producto = productos.get(index);
            byte[] imagenBytes = producto.getImagen();
            System.out.println("haber"+producto);
            
        

           
            ImageIcon icono = new ImageIcon(imagenBytes);
            Image imagenEscalada = icono.getImage().getScaledInstance(300, 328, Image.SCALE_SMOOTH);
            haber.setIcon(new ImageIcon(imagenEscalada));


            
            lb_nombre.setText(producto.getNombre());
            lb_precio.setText("Precio: $" + producto.getPreciovent());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al cargar la imagen", "Error", JOptionPane.ERROR_MESSAGE);
        }
        
    }
    private void actualizarPuntos() {
        for (int i = 0; i < puntos.size(); i++) {
            if (i == index) {
                puntos.get(i).setForeground(Color.WHITE); 
            } else {
                puntos.get(i).setForeground(Color.GRAY); 
            }
        }
    }


    
    

    private void cargarGaleriaPorCategoria(String categoria) {
    // Limpiar Galeria antes de cargar productos
    Galeria.removeAll();
    Galeria.setLayout(new GridLayout(2, 4, 5, 5)); // Distribuir en 3 columnas

    // Obtener la lista de productos por categoría
    TablaProductos tablaProductos = new TablaProductos();
    List<Producto> productos = tablaProductos.listarProductosPorCategoria(categoria);

    if (productos == null || productos.isEmpty()) {
        JLabel mensajeVacio = new JLabel("No hay productos disponibles en esta categoría.");
        mensajeVacio.setHorizontalAlignment(SwingConstants.CENTER);
        Galeria.add(mensajeVacio);
    } else {
        // Agrupar productos por nombre
        Map<String, List<Producto>> productosAgrupados = agruparProductosPorNombre(productos);
        for (Map.Entry<String, List<Producto>> entry : productosAgrupados.entrySet()) {
            String nombreProducto = entry.getKey();
            List<Producto> variantes = entry.getValue();
            JPanel panelProducto = new JPanel(new BorderLayout());
            Producto productoPrincipal = variantes.get(0);
            JPanel infoPanel = new JPanel(new GridLayout(0, 1));
            
            // Mostrar la imagen del producto principal
            JLabel lblImagen = new JLabel();
            try {
                ImageIcon icon = new ImageIcon(productoPrincipal.getImagen());
                Image img = icon.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
                lblImagen.setIcon(new ImageIcon(img));
            } catch (Exception e) {
                lblImagen.setText("Imagen no disponible");
                lblImagen.setHorizontalAlignment(SwingConstants.CENTER);
            }
            infoPanel.add(lblImagen);

            // Mostrar nombre y precio del producto
            JLabel lblNombre = new JLabel("Nombre: " + productoPrincipal.getNombre());
            JLabel lblPrecio = new JLabel("Precio: S/ " + productoPrincipal.getPreciovent());
            infoPanel.add(lblNombre);
            infoPanel.add(lblPrecio);

            // Añadir el panel de información al panel principal
            panelProducto.add(infoPanel, BorderLayout.NORTH);

            // Crear botón "Seleccionar" para el producto
            JButton btnSeleccionar = new JButton("Seleccionar");
            btnSeleccionar.addActionListener(e -> mostrarDetalleProducto(productoPrincipal, variantes));
            
            panelProducto.add(btnSeleccionar, BorderLayout.SOUTH);
            

            // Añadir el panel de producto a la Galeria
            Galeria.add(panelProducto);
        }
    }

    // Actualizar la interfaz
    Galeria.revalidate();
    Galeria.repaint();
    }
    private JPanel crearPanelProductoAgrupado(Producto productoPrincipal, List<Producto> variantes) {
        JPanel panel = new JPanel(new BorderLayout());

    // Mostrar la imagen del producto
        JLabel lblImagen = new JLabel();
        try {
            ImageIcon icon = new ImageIcon(productoPrincipal.getImagen());
            Image img = icon.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
            lblImagen.setIcon(new ImageIcon(img));
        } catch (Exception e) {
            lblImagen.setText("Imagen no disponible");
            lblImagen.setHorizontalAlignment(SwingConstants.CENTER);
        }
        panel.add(lblImagen, BorderLayout.CENTER);

    
        JButton btnSeleccionar = new JButton("Seleccionar");
        btnSeleccionar.addActionListener(e -> mostrarDetalleProducto(productoPrincipal, variantes));
        panel.add(btnSeleccionar, BorderLayout.SOUTH);

        return panel;
    }

    private void mostrarDetalleProducto(Producto productoPrincipal, List<Producto> variantes) {
    
        lblNombre.setText("");
        lblPrecio.setText("");
        lblStock.setText("");
        lblSexo.setText("");
        lblImagen.setIcon(null);
        PanelTallas.removeAll(); 

   
        lblNombre.setText("Nombre: " + productoPrincipal.getNombre());
        lblPrecio.setText("Precio: S/ " + productoPrincipal.getPreciovent());
        lblStock.setText("Stock: " + productoPrincipal.getStock());
        lblSexo.setText("Sexo: " + productoPrincipal.getSexo());

    // Cargar la imagen del producto principal
    try {
        ImageIcon icon = new ImageIcon(productoPrincipal.getImagen());
        Image img = icon.getImage().getScaledInstance(lblImagen.getWidth(), lblImagen.getHeight(), Image.SCALE_SMOOTH);
        lblImagen.setIcon(new ImageIcon(img));
    } catch (Exception ex) {
        lblImagen.setText("Imagen no disponible");
        lblImagen.setIcon(null);
    }

    // Agregar los botones de talla
    if (variantes != null && !variantes.isEmpty()) {
        for (Producto p : variantes) {
            if (p.getTalla() != null && !p.getTalla().isEmpty()) {
                JButton btnTalla = new JButton(p.getTalla());
                btnTalla.addActionListener(e -> {
                    // Actualizar los detalles del producto según la talla seleccionada
                    lblPrecio.setText("Precio: S/ " + p.getPreciovent());
                    lblStock.setText("Stock: " + p.getStock());
                    try {
                        ImageIcon iconTalla = new ImageIcon(p.getImagen());
                        Image imgTalla = iconTalla.getImage().getScaledInstance(lblImagen.getWidth(), lblImagen.getHeight(), Image.SCALE_SMOOTH);
                        lblImagen.setIcon(new ImageIcon(imgTalla));
                    } catch (Exception ex) {
                        lblImagen.setText("Imagen no disponible");
                        lblImagen.setIcon(null);
                    }
                });
                PanelTallas.add(btnTalla); 
            }
        }
    } else {
        JLabel lblSinTallas = new JLabel("No hay tallas disponibles");
        lblSinTallas.setHorizontalAlignment(SwingConstants.CENTER);
        PanelTallas.add(lblSinTallas);
    }

    // Actualizar el panel de tallas
    PanelTallas.revalidate();
    PanelTallas.repaint();
    Pestañas.setSelectedIndex(3);
}

    public Map<String, List<Producto>> agruparProductosPorNombre(List<Producto> productos) {
        Map<String, List<Producto>> productosAgrupados = new HashMap<>();
    for (Producto producto : productos) {
        String nombre = producto.getNombre();
        if (!productosAgrupados.containsKey(nombre)) {
            productosAgrupados.put(nombre, new ArrayList<>());
        }
        productosAgrupados.get(nombre).add(producto);
    }
    return productosAgrupados;
    }


    
        
    

    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Pestañas = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        btn_siguiente = new javax.swing.JButton();
        Panelpuntos = new javax.swing.JPanel();
        haber = new javax.swing.JLabel();
        lb_nombre = new javax.swing.JLabel();
        lb_precio = new javax.swing.JLabel();
        btn_generar = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        btn_consultas = new javax.swing.JButton();
        btn_reporte = new javax.swing.JButton();
        puntitos = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        btn_atras = new javax.swing.JButton();
        btn_Venta = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        btn_Atras = new javax.swing.JButton();

        btn_polo = new javax.swing.JButton();
        btn_pantalones = new javax.swing.JButton();
        btn_casacas = new javax.swing.JButton();
        Deportivo = new javax.swing.JPanel();
        btn_salir = new javax.swing.JButton();
        Galeria = new javax.swing.JPanel();
        pantalones = new javax.swing.JPanel();
        PanelTallas = new javax.swing.JPanel();
        lblNombre = new javax.swing.JLabel();
        lblSexo = new javax.swing.JLabel();
        lblImagen = new javax.swing.JLabel();
        lblPrecio = new javax.swing.JLabel();
        btn_carrito = new javax.swing.JButton();
        btn_atras2 = new javax.swing.JButton();
        lblStock = new javax.swing.JLabel();

        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();

        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();

        setPreferredSize(new java.awt.Dimension(1900, 1000));
        setLayout(new java.awt.BorderLayout());

        jLabel3.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        jLabel3.setText("REPORTE");

        btn_siguiente.setText("siguiente");
        btn_siguiente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_siguienteActionPerformed(evt);
            }
        });

        Panelpuntos.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        haber.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lb_nombre.setText("jLabel6");

        lb_precio.setText("jLabel1");

        javax.swing.GroupLayout PanelpuntosLayout = new javax.swing.GroupLayout(Panelpuntos);
        Panelpuntos.setLayout(PanelpuntosLayout);
        PanelpuntosLayout.setHorizontalGroup(
            PanelpuntosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelpuntosLayout.createSequentialGroup()
                .addGap(95, 95, 95)
                .addComponent(lb_nombre)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 138, Short.MAX_VALUE)
                .addComponent(haber, javax.swing.GroupLayout.PREFERRED_SIZE, 456, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(133, 133, 133)
                .addComponent(lb_precio)
                .addGap(92, 92, 92))
        );
        PanelpuntosLayout.setVerticalGroup(
            PanelpuntosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelpuntosLayout.createSequentialGroup()
                .addGroup(PanelpuntosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelpuntosLayout.createSequentialGroup()
                        .addGap(121, 121, 121)
                        .addComponent(lb_nombre))
                    .addGroup(PanelpuntosLayout.createSequentialGroup()
                        .addGap(131, 131, 131)
                        .addComponent(lb_precio, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(191, Short.MAX_VALUE))
            .addComponent(haber, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        btn_generar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/orden.png"))); // NOI18N
        btn_generar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_generarActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        jLabel5.setText("GENERAR ORDEN");

        btn_consultas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/consultas.png"))); // NOI18N
        btn_consultas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_consultasActionPerformed(evt);
            }
        });

        btn_reporte.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/reporte.png"))); // NOI18N
        btn_reporte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_reporteActionPerformed(evt);
            }
        });

        puntitos.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout puntitosLayout = new javax.swing.GroupLayout(puntitos);
        puntitos.setLayout(puntitosLayout);
        puntitosLayout.setHorizontalGroup(
            puntitosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 165, Short.MAX_VALUE)
        );
        puntitosLayout.setVerticalGroup(
            puntitosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 26, Short.MAX_VALUE)
        );

        jLabel4.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        jLabel4.setText("CONSULTAS");

        btn_atras.setText("atras");
        btn_atras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_atrasActionPerformed(evt);
            }
        });

        btn_Venta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/sale.png"))); // NOI18N
        btn_Venta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_VentaActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        jLabel2.setText("VENTA");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btn_atras)
                        .addGap(119, 119, 119)
                        .addComponent(Panelpuntos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_siguiente))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(611, 611, 611)
                        .addComponent(puntitos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(341, 341, 341)
                        .addComponent(jLabel2)
                        .addGap(484, 484, 484)
                        .addComponent(jLabel4))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(291, 291, 291)
                                .addComponent(btn_Venta, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(273, 273, 273)
                                .addComponent(btn_reporte, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(387, 387, 387)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btn_generar, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_consultas, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(323, 323, 323)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(460, 460, 460)
                        .addComponent(jLabel5)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(146, 146, 146)
                                .addComponent(btn_atras))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(158, 158, 158)
                                .addComponent(btn_siguiente)))
                        .addGap(175, 175, 175))
                    .addComponent(Panelpuntos, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(puntitos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(66, 66, 66)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btn_Venta, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jLabel5)))
                    .addComponent(btn_consultas, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btn_generar, javax.swing.GroupLayout.DEFAULT_SIZE, 173, Short.MAX_VALUE)
                    .addComponent(btn_reporte, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))

                .addContainerGap(56, Short.MAX_VALUE))

                .addContainerGap(231, Short.MAX_VALUE))

        );

        Pestañas.addTab("Inicio", jPanel1);

        jPanel3.setBackground(new java.awt.Color(153, 255, 204));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("CAMISAS");
        jPanel3.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 180, -1, -1));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel6.setText("PANTALONES");
        jPanel3.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 330, -1, -1));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel7.setText("BUZOS");
        jPanel3.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 360, -1, -1));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel8.setText("CASACAS");
        jPanel3.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 540, -1, -1));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel9.setText("ZAPATOS");
        jPanel3.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 550, -1, -1));

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel10.setText("POLOS");
        jPanel3.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 180, -1, -1));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/pantalones.jpg"))); // NOI18N
        jButton1.setBorder(null);
        jPanel3.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 240, -1, 70));

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/camisas_1.jpg"))); // NOI18N
        jButton2.setBorder(null);
        jPanel3.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 50, 200, 100));

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/buzos.jpg"))); // NOI18N
        jButton3.setBorder(null);
        jPanel3.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 220, 120, 120));

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/casaca (1).jpg"))); // NOI18N
        jButton4.setBorder(null);
        jPanel3.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 390, 130, -1));

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/zapatos (1).jpg"))); // NOI18N
        jButton5.setBorder(null);
        jPanel3.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 410, -1, 120));

        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/polos.jpg"))); // NOI18N
        jButton6.setBorder(null);
        jPanel3.add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 50, -1, 110));

        btn_Atras.setText("Atras");
        btn_Atras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_AtrasActionPerformed(evt);
            }
        });
        jPanel3.add(btn_Atras, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 780, -1, -1));

        jButton7.setText("Deportivo");
        jPanel3.add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 180, -1, -1));

        jButton8.setText("Elegante");
        jPanel3.add(jButton8, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 290, -1, -1));

        jButton9.setText("Casual");
        jPanel3.add(jButton9, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 440, -1, -1));

        Pestañas.addTab("venta", jPanel3);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1900, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1098, Short.MAX_VALUE)
        );

        Pestañas.addTab("Deportivo", jPanel4);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1900, Short.MAX_VALUE)
        );

        DeportivoLayout.setVerticalGroup(
            DeportivoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DeportivoLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(btn_salir)
                .addContainerGap(876, Short.MAX_VALUE))
            .addComponent(Galeria, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        Pestañas.addTab("polo", Deportivo);

        PanelTallas.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout PanelTallasLayout = new javax.swing.GroupLayout(PanelTallas);
        PanelTallas.setLayout(PanelTallasLayout);
        PanelTallasLayout.setHorizontalGroup(
            PanelTallasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 318, Short.MAX_VALUE)
        );
        PanelTallasLayout.setVerticalGroup(
            PanelTallasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 134, Short.MAX_VALUE)
        );

        lblNombre.setText("jLabel11");

        lblSexo.setText("lblPrecio");

        lblImagen.setText("jLabel12");
        lblImagen.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblPrecio.setText("jLabel12");

        btn_carrito.setText("Añadir al carrito");

        btn_atras2.setText("atras");
        btn_atras2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_atras2ActionPerformed(evt);
            }
        });

        lblStock.setText("jLabel11");

        javax.swing.GroupLayout pantalonesLayout = new javax.swing.GroupLayout(pantalones);
        pantalones.setLayout(pantalonesLayout);
        pantalonesLayout.setHorizontalGroup(
            pantalonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pantalonesLayout.createSequentialGroup()
                .addGroup(pantalonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pantalonesLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btn_atras2))
                    .addGroup(pantalonesLayout.createSequentialGroup()
                        .addGap(249, 249, 249)
                        .addComponent(lblStock))
                    .addGroup(pantalonesLayout.createSequentialGroup()
                        .addGap(315, 315, 315)
                        .addGroup(pantalonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pantalonesLayout.createSequentialGroup()
                                .addComponent(lblSexo)
                                .addGap(289, 289, 289)
                                .addComponent(PanelTallas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lblNombre)))
                    .addGroup(pantalonesLayout.createSequentialGroup()
                        .addGap(118, 118, 118)
                        .addComponent(lblPrecio)
                        .addGap(56, 56, 56)
                        .addComponent(lblImagen, javax.swing.GroupLayout.PREFERRED_SIZE, 326, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(155, 155, 155)
                        .addComponent(btn_carrito)))
                .addContainerGap(930, Short.MAX_VALUE))
        );
        pantalonesLayout.setVerticalGroup(
            pantalonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pantalonesLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(btn_atras2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pantalonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pantalonesLayout.createSequentialGroup()
                        .addComponent(lblNombre)
                        .addGap(63, 63, 63)
                        .addComponent(lblSexo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_carrito)
                        .addGap(417, 417, 417))
                    .addGroup(pantalonesLayout.createSequentialGroup()
                        .addGroup(pantalonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pantalonesLayout.createSequentialGroup()
                                .addGroup(pantalonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(pantalonesLayout.createSequentialGroup()
                                        .addGap(276, 276, 276)
                                        .addComponent(lblPrecio))
                                    .addGroup(pantalonesLayout.createSequentialGroup()
                                        .addGap(150, 150, 150)
                                        .addComponent(lblImagen, javax.swing.GroupLayout.PREFERRED_SIZE, 311, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(86, 86, 86)
                                .addComponent(lblStock))
                            .addGroup(pantalonesLayout.createSequentialGroup()
                                .addGap(37, 37, 37)
                                .addComponent(PanelTallas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(300, Short.MAX_VALUE))))
        );

        Pestañas.addTab("pantalones", pantalones);

        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1098, Short.MAX_VALUE)
        );

        Pestañas.addTab("Elegante", jPanel2);


        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1900, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)

            .addGap(0, 923, Short.MAX_VALUE)

            .addGap(0, 1098, Short.MAX_VALUE)

        );

        Pestañas.addTab("Casual", jPanel5);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1900, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)

            .addGap(0, 923, Short.MAX_VALUE)

            .addGap(0, 1098, Short.MAX_VALUE)

        );

        Pestañas.addTab("Acsesorios", jPanel6);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1900, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)

            .addGap(0, 923, Short.MAX_VALUE)

            .addGap(0, 1098, Short.MAX_VALUE)

        );

        Pestañas.addTab("consulta", jPanel7);

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1900, Short.MAX_VALUE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)

            .addGap(0, 923, Short.MAX_VALUE)

            .addGap(0, 1098, Short.MAX_VALUE)

        );

        Pestañas.addTab("reporte", jPanel8);

        add(Pestañas, java.awt.BorderLayout.PAGE_START);
    }// </editor-fold>//GEN-END:initComponents

    private void btn_VentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_VentaActionPerformed
        // TODO add your handling code here:
        Pestañas.setSelectedIndex(1);

    }//GEN-LAST:event_btn_VentaActionPerformed

    private void btn_consultasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_consultasActionPerformed
        // TODO add your handling code here:
        

    }//GEN-LAST:event_btn_consultasActionPerformed

    private void btn_generarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_generarActionPerformed
        // TODO add your handling code here:
        

    }//GEN-LAST:event_btn_generarActionPerformed

    private void btn_reporteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_reporteActionPerformed
        // TODO add your handling code here:
        

    }//GEN-LAST:event_btn_reporteActionPerformed

    private void btn_atrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_atrasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_atrasActionPerformed

    private void btn_siguienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_siguienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_siguienteActionPerformed

    private void btn_AtrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_AtrasActionPerformed
        // TODO add your handling code here:
        Pestañas.setSelectedIndex(0);
    }//GEN-LAST:event_btn_AtrasActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables

    private javax.swing.JPanel Deportivo;
    private javax.swing.JPanel Galeria;
    private javax.swing.JPanel Inicio;
    private javax.swing.JPanel PanelTallas;


    private javax.swing.JPanel Panelpuntos;
    private javax.swing.JTabbedPane Pestañas;
    private javax.swing.JButton btn_Atras;
    private javax.swing.JButton btn_Venta;
    private javax.swing.JButton btn_atras;
    private javax.swing.JButton btn_consultas;
    private javax.swing.JButton btn_generar;
    private javax.swing.JButton btn_reporte;
    private javax.swing.JButton btn_siguiente;
    private javax.swing.JLabel haber;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JLabel lb_nombre;
    private javax.swing.JLabel lb_precio;

    private javax.swing.JLabel lblImagen;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblPrecio;
    private javax.swing.JLabel lblSexo;
    private javax.swing.JLabel lblStock;
    private javax.swing.JPanel pantalones;


    private javax.swing.JPanel puntitos;
    // End of variables declaration//GEN-END:variables
}
