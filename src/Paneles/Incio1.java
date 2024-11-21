
package Paneles;

import Clases.Producto;
import FuncionamientoTablas.TablaProductos;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;


public class Incio1 extends javax.swing.JPanel {
    
    Producto pro =new Producto();
    TablaProductos proDao = new TablaProductos();
    List<Producto> productos =proDao.ListadeProductos();
    private List<JLabel> puntos = new ArrayList<>();
    
    private int index = 0;
            
    
    
    
    
    
    public Incio1() {
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
        btn_polo.addActionListener(e -> cargarGaleriaPorCategoria("Polos"));
        btn_pantalones.addActionListener(e -> cargarGaleriaPorCategoria("Pantalones"));
        btn_casacas.addActionListener(e -> cargarGaleriaPorCategoria("Casacas"));
        
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
        lblSexo.setText("Para: " + productoPrincipal.getSexo());

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
        Inicio = new javax.swing.JPanel();
        btn_atras = new javax.swing.JButton();
        btn_siguiente = new javax.swing.JButton();
        jPanelBaner = new javax.swing.JPanel();
        haber = new javax.swing.JLabel();
        lb_nombre = new javax.swing.JLabel();
        lb_precio = new javax.swing.JLabel();
        puntitos = new javax.swing.JPanel();
        btn_VENTANA = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        btn_polo = new javax.swing.JButton();
        btn_pantalones = new javax.swing.JButton();
        btn_casacas = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        Galeria = new javax.swing.JPanel();
        btn_salir = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        lblImagen = new javax.swing.JLabel();
        lblNombre = new javax.swing.JLabel();
        lblPrecio = new javax.swing.JLabel();
        lblStock = new javax.swing.JLabel();
        lblSexo = new javax.swing.JLabel();
        PanelTallas = new javax.swing.JPanel();

        btn_atras.setText("Atras");

        btn_siguiente.setText("Siguiente");

        lb_nombre.setText("a");

        lb_precio.setText("jLabel1");

        javax.swing.GroupLayout jPanelBanerLayout = new javax.swing.GroupLayout(jPanelBaner);
        jPanelBaner.setLayout(jPanelBanerLayout);
        jPanelBanerLayout.setHorizontalGroup(
            jPanelBanerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelBanerLayout.createSequentialGroup()
                .addGap(105, 105, 105)
                .addComponent(lb_nombre, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(66, 66, 66)
                .addComponent(haber, javax.swing.GroupLayout.PREFERRED_SIZE, 434, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(94, 94, 94)
                .addComponent(lb_precio)
                .addContainerGap(233, Short.MAX_VALUE))
        );
        jPanelBanerLayout.setVerticalGroup(
            jPanelBanerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(haber, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelBanerLayout.createSequentialGroup()
                .addContainerGap(140, Short.MAX_VALUE)
                .addGroup(jPanelBanerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelBanerLayout.createSequentialGroup()
                        .addComponent(lb_nombre, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(124, 124, 124))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelBanerLayout.createSequentialGroup()
                        .addComponent(lb_precio)
                        .addGap(138, 138, 138))))
        );

        puntitos.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout puntitosLayout = new javax.swing.GroupLayout(puntitos);
        puntitos.setLayout(puntitosLayout);
        puntitosLayout.setHorizontalGroup(
            puntitosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 46, Short.MAX_VALUE)
        );
        puntitosLayout.setVerticalGroup(
            puntitosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 26, Short.MAX_VALUE)
        );

        btn_VENTANA.setText("VENTANA");
        btn_VENTANA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_VENTANAActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout InicioLayout = new javax.swing.GroupLayout(Inicio);
        Inicio.setLayout(InicioLayout);
        InicioLayout.setHorizontalGroup(
            InicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(InicioLayout.createSequentialGroup()
                .addGroup(InicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(InicioLayout.createSequentialGroup()
                        .addGap(180, 180, 180)
                        .addComponent(btn_atras)
                        .addGap(35, 35, 35)
                        .addComponent(jPanelBaner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_siguiente))
                    .addGroup(InicioLayout.createSequentialGroup()
                        .addGap(749, 749, 749)
                        .addComponent(puntitos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(InicioLayout.createSequentialGroup()
                        .addGap(367, 367, 367)
                        .addComponent(btn_VENTANA)))
                .addContainerGap(257, Short.MAX_VALUE))
        );
        InicioLayout.setVerticalGroup(
            InicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(InicioLayout.createSequentialGroup()
                .addGroup(InicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(InicioLayout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(jPanelBaner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(InicioLayout.createSequentialGroup()
                        .addGap(180, 180, 180)
                        .addComponent(btn_atras))
                    .addGroup(InicioLayout.createSequentialGroup()
                        .addGap(183, 183, 183)
                        .addComponent(btn_siguiente)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(puntitos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(150, 150, 150)
                .addComponent(btn_VENTANA)
                .addContainerGap(247, Short.MAX_VALUE))
        );

        Pestañas.addTab("Inicio", Inicio);

        btn_polo.setText("jButton1");
        btn_polo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_poloActionPerformed(evt);
            }
        });

        btn_pantalones.setText("jButton1");
        btn_pantalones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_pantalonesActionPerformed(evt);
            }
        });

        btn_casacas.setText("jButton1");
        btn_casacas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_casacasActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(224, 224, 224)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_casacas)
                    .addComponent(btn_pantalones)
                    .addComponent(btn_polo))
                .addContainerGap(1363, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(143, 143, 143)
                .addComponent(btn_polo)
                .addGap(106, 106, 106)
                .addComponent(btn_pantalones)
                .addGap(59, 59, 59)
                .addComponent(btn_casacas)
                .addContainerGap(396, Short.MAX_VALUE))
        );

        Pestañas.addTab("Categoria", jPanel3);

        javax.swing.GroupLayout GaleriaLayout = new javax.swing.GroupLayout(Galeria);
        Galeria.setLayout(GaleriaLayout);
        GaleriaLayout.setHorizontalGroup(
            GaleriaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1578, Short.MAX_VALUE)
        );
        GaleriaLayout.setVerticalGroup(
            GaleriaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        btn_salir.setText("Atras");
        btn_salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_salirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(btn_salir)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Galeria, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addComponent(btn_salir)
                .addContainerGap(694, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Galeria, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        Pestañas.addTab("Galeria", jPanel2);

        lblImagen.setText("jLabel1");

        lblNombre.setText("lblNombre");

        lblPrecio.setText("jLabel1");

        lblStock.setText("lblStock");

        lblSexo.setText("lblSexo");

        javax.swing.GroupLayout PanelTallasLayout = new javax.swing.GroupLayout(PanelTallas);
        PanelTallas.setLayout(PanelTallasLayout);
        PanelTallasLayout.setHorizontalGroup(
            PanelTallasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 204, Short.MAX_VALUE)
        );
        PanelTallasLayout.setVerticalGroup(
            PanelTallasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 43, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(216, 216, 216)
                        .addComponent(lblNombre))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(156, 156, 156)
                                .addComponent(lblImagen, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGap(234, 234, 234)
                                .addComponent(PanelTallas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(96, 96, 96)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblSexo)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(lblPrecio)
                                .addComponent(lblStock)))))
                .addContainerGap(1086, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(113, 113, 113)
                .addComponent(lblNombre)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblImagen, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblStock)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(52, 52, 52)
                        .addComponent(lblPrecio)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblSexo))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(PanelTallas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(310, Short.MAX_VALUE))
        );

        Pestañas.addTab("Descipcion", jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Pestañas)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Pestañas)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btn_VENTANAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_VENTANAActionPerformed
        // TODO add your handling code here:
        Pestañas.setSelectedIndex(1);
    }//GEN-LAST:event_btn_VENTANAActionPerformed

    private void btn_salirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_salirActionPerformed
        // TODO add your handling code here:
        Pestañas.setSelectedIndex(0);
    }//GEN-LAST:event_btn_salirActionPerformed

    private void btn_poloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_poloActionPerformed
        // TODO add your handling code here:
        Pestañas.setSelectedIndex(0);
    }//GEN-LAST:event_btn_poloActionPerformed

    private void btn_pantalonesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_pantalonesActionPerformed
        // TODO add your handling code here:
        Pestañas.setSelectedIndex(0);
    }//GEN-LAST:event_btn_pantalonesActionPerformed

    private void btn_casacasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_casacasActionPerformed
        // TODO add your handling code here:
        Pestañas.setSelectedIndex(0);
    }//GEN-LAST:event_btn_casacasActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Galeria;
    private javax.swing.JPanel Inicio;
    private javax.swing.JPanel PanelTallas;
    private javax.swing.JTabbedPane Pestañas;
    private javax.swing.JButton btn_VENTANA;
    private javax.swing.JButton btn_atras;
    private javax.swing.JButton btn_casacas;
    private javax.swing.JButton btn_pantalones;
    private javax.swing.JButton btn_polo;
    private javax.swing.JButton btn_salir;
    private javax.swing.JButton btn_siguiente;
    private javax.swing.JLabel haber;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanelBaner;
    private javax.swing.JLabel lb_nombre;
    private javax.swing.JLabel lb_precio;
    private javax.swing.JLabel lblImagen;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblPrecio;
    private javax.swing.JLabel lblSexo;
    private javax.swing.JLabel lblStock;
    private javax.swing.JPanel puntitos;
    // End of variables declaration//GEN-END:variables
}
