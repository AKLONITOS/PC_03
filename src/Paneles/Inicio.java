
package Paneles;

import Clases.Producto;
import FuncionamientoTablas.TablaProductos;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
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
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;
import javax.swing.Timer;



public class Inicio extends javax.swing.JPanel {
    TablaProductos proDao = new TablaProductos();
    List<Producto> productos =proDao.ListadeProductos();
    private List<JLabel> puntos = new ArrayList<>();
    private JScrollPane scrollPane; 
    private int index = 0;
    
    public Inicio() {
        initComponents();
        
        //ocultar pestañas
        
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
        btn_salir.addActionListener(new ActionListener() {
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
        Galeria.removeAll(); 
        Galeria.setLayout(new GridLayout(0, 3, 10, 10));  // Diseño de rejilla

        TablaProductos tablaProductos = new TablaProductos();
        List<Producto> productos = tablaProductos.listarProductosPorCategoria(categoria);

        if (productos.isEmpty()) {
            JLabel mensajeVacio = new JLabel("No hay productos disponibles en esta categoría.");
            mensajeVacio.setHorizontalAlignment(SwingConstants.CENTER);
            Galeria.add(mensajeVacio);
        } else {
            for (Producto producto : productos) {
                JPanel panelProducto = crearPanelProducto(producto);
                Galeria.add(panelProducto);
            }
        }

        Galeria.revalidate();
        Galeria.repaint();
    }
    private JPanel crearPanelProducto(Producto producto) {
        JPanel panel = new JPanel(new BorderLayout());

        // Imagen del producto
        JLabel lblImagen;
        try {
            ImageIcon imageIcon = new ImageIcon(producto.getImagen());
            Image image = imageIcon.getImage();
            Image resizedImage = image.getScaledInstance(150, 150, Image.SCALE_SMOOTH);
            lblImagen = new JLabel(new ImageIcon(resizedImage));
        } catch (Exception e) {
            lblImagen = new JLabel("Imagen no disponible");
        }
        lblImagen.setHorizontalAlignment(SwingConstants.CENTER);

        // Botón para seleccionar el producto
        JButton btnSeleccionar = new JButton("Seleccionar");
        btnSeleccionar.addActionListener(e -> {
        mostrarDetalleProducto(producto);
        Pestañas.setSelectedIndex(3);
    });

        panel.add(lblImagen, BorderLayout.CENTER);
        panel.add(btnSeleccionar, BorderLayout.SOUTH);

        return panel;
    }

    private void mostrarDetalleProducto(Producto producto) {
    
    if (producto != null) {
        // Actualizar el nombre
        lblNombre.setText("Nombre: " + producto.getNombre());

        // Actualizar el sexo
        lblSexo.setText("Sexo: " + producto.getSexo());

        // Actualizar el precio
        lblPrecio.setText("Precio: S/ " + producto.getPreciovent());

        // Actualizar la imagen
        try {
            ImageIcon icon = new ImageIcon(producto.getImagen());
            Image img = icon.getImage().getScaledInstance(lblImagen.getWidth(), lblImagen.getHeight(), Image.SCALE_SMOOTH);
            lblImagen.setIcon(new ImageIcon(img));
        } catch (Exception e) {
            lblImagen.setText("Imagen no disponible");
            lblImagen.setIcon(null);
        }
    } else {
        
        lblNombre.setText("Nombre: No disponible");
        lblSexo.setText("Sexo: No disponible");
        lblPrecio.setText("Precio: No disponible");
        lblImagen.setText("Imagen no disponible");
        lblImagen.setIcon(null);
    }

    
    
}
    
   

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Pestañas = new javax.swing.JTabbedPane();
        Inicio = new javax.swing.JPanel();
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
        venta = new javax.swing.JPanel();
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
        lblNombre = new javax.swing.JLabel();
        lblSexo = new javax.swing.JLabel();
        lblImagen = new javax.swing.JLabel();
        lblPrecio = new javax.swing.JLabel();
        btn_carrito = new javax.swing.JButton();
        btn_atras2 = new javax.swing.JButton();
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

        javax.swing.GroupLayout InicioLayout = new javax.swing.GroupLayout(Inicio);
        Inicio.setLayout(InicioLayout);
        InicioLayout.setHorizontalGroup(
            InicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(InicioLayout.createSequentialGroup()
                .addGroup(InicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(InicioLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btn_atras)
                        .addGap(119, 119, 119)
                        .addComponent(Panelpuntos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_siguiente))
                    .addGroup(InicioLayout.createSequentialGroup()
                        .addGap(611, 611, 611)
                        .addComponent(puntitos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(InicioLayout.createSequentialGroup()
                        .addGap(341, 341, 341)
                        .addComponent(jLabel2)
                        .addGap(484, 484, 484)
                        .addComponent(jLabel4))
                    .addGroup(InicioLayout.createSequentialGroup()
                        .addGroup(InicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(InicioLayout.createSequentialGroup()
                                .addGap(291, 291, 291)
                                .addComponent(btn_Venta, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(InicioLayout.createSequentialGroup()
                                .addGap(273, 273, 273)
                                .addComponent(btn_reporte, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(387, 387, 387)
                        .addGroup(InicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btn_generar, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_consultas, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(InicioLayout.createSequentialGroup()
                        .addGap(323, 323, 323)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(460, 460, 460)
                        .addComponent(jLabel5)))
                .addContainerGap())
        );
        InicioLayout.setVerticalGroup(
            InicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, InicioLayout.createSequentialGroup()
                .addGroup(InicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(InicioLayout.createSequentialGroup()
                        .addGroup(InicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(InicioLayout.createSequentialGroup()
                                .addGap(146, 146, 146)
                                .addComponent(btn_atras))
                            .addGroup(InicioLayout.createSequentialGroup()
                                .addGap(158, 158, 158)
                                .addComponent(btn_siguiente)))
                        .addGap(175, 175, 175))
                    .addComponent(Panelpuntos, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(puntitos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(66, 66, 66)
                .addGroup(InicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addGroup(InicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(InicioLayout.createSequentialGroup()
                        .addComponent(btn_Venta, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addGroup(InicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jLabel5)))
                    .addComponent(btn_consultas, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(InicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btn_generar, javax.swing.GroupLayout.DEFAULT_SIZE, 173, Short.MAX_VALUE)
                    .addComponent(btn_reporte, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(52, Short.MAX_VALUE))
        );

        Pestañas.addTab("Inicio", Inicio);

        venta.setBackground(new java.awt.Color(153, 255, 204));
        venta.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("CAMISAS");
        venta.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 180, -1, -1));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel6.setText("PANTALONES");
        venta.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 330, -1, -1));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel7.setText("BUZOS");
        venta.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 360, -1, -1));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel8.setText("CASACAS");
        venta.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 540, -1, -1));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel9.setText("ZAPATOS");
        venta.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 550, -1, -1));

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel10.setText("POLOS");
        venta.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 180, -1, -1));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/pantalones.jpg"))); // NOI18N
        jButton1.setBorder(null);
        venta.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 240, -1, 70));

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/camisas_1.jpg"))); // NOI18N
        jButton2.setBorder(null);
        venta.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 50, 200, 100));

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/buzos.jpg"))); // NOI18N
        jButton3.setBorder(null);
        venta.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 220, 120, 120));

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/casaca (1).jpg"))); // NOI18N
        jButton4.setBorder(null);
        venta.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 390, 130, -1));

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/zapatos (1).jpg"))); // NOI18N
        jButton5.setBorder(null);
        venta.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 410, -1, 120));

        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/polos.jpg"))); // NOI18N
        jButton6.setBorder(null);
        venta.add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 50, -1, 110));

        btn_Atras.setText("Atras");
        btn_Atras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_AtrasActionPerformed(evt);
            }
        });
        venta.add(btn_Atras, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 580, -1, -1));

        btn_polo.setText("Polos");
        btn_polo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_poloActionPerformed(evt);
            }
        });
        venta.add(btn_polo, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 180, -1, -1));

        btn_pantalones.setText("Pantalones");
        btn_pantalones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_pantalonesActionPerformed(evt);
            }
        });
        venta.add(btn_pantalones, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 290, -1, -1));

        btn_casacas.setText("casacas");
        venta.add(btn_casacas, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 440, -1, -1));

        Pestañas.addTab("venta", venta);

        btn_salir.setText("atras");
        btn_salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_salirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout GaleriaLayout = new javax.swing.GroupLayout(Galeria);
        Galeria.setLayout(GaleriaLayout);
        GaleriaLayout.setHorizontalGroup(
            GaleriaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1810, Short.MAX_VALUE)
        );
        GaleriaLayout.setVerticalGroup(
            GaleriaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout DeportivoLayout = new javax.swing.GroupLayout(Deportivo);
        Deportivo.setLayout(DeportivoLayout);
        DeportivoLayout.setHorizontalGroup(
            DeportivoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DeportivoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btn_salir)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Galeria, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        DeportivoLayout.setVerticalGroup(
            DeportivoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DeportivoLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(btn_salir)
                .addContainerGap(872, Short.MAX_VALUE))
            .addComponent(Galeria, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        Pestañas.addTab("polo", Deportivo);

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

        javax.swing.GroupLayout pantalonesLayout = new javax.swing.GroupLayout(pantalones);
        pantalones.setLayout(pantalonesLayout);
        pantalonesLayout.setHorizontalGroup(
            pantalonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pantalonesLayout.createSequentialGroup()
                .addGroup(pantalonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pantalonesLayout.createSequentialGroup()
                        .addGap(118, 118, 118)
                        .addComponent(lblPrecio)
                        .addGap(56, 56, 56)
                        .addComponent(lblImagen, javax.swing.GroupLayout.PREFERRED_SIZE, 326, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pantalonesLayout.createSequentialGroup()
                        .addGap(315, 315, 315)
                        .addGroup(pantalonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblSexo)
                            .addComponent(lblNombre)))
                    .addGroup(pantalonesLayout.createSequentialGroup()
                        .addGap(320, 320, 320)
                        .addComponent(btn_carrito))
                    .addGroup(pantalonesLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btn_atras2)))
                .addContainerGap(1357, Short.MAX_VALUE))
        );
        pantalonesLayout.setVerticalGroup(
            pantalonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pantalonesLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(btn_atras2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblNombre)
                .addGap(63, 63, 63)
                .addComponent(lblSexo)
                .addGroup(pantalonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pantalonesLayout.createSequentialGroup()
                        .addGap(177, 177, 177)
                        .addComponent(lblPrecio))
                    .addGroup(pantalonesLayout.createSequentialGroup()
                        .addGap(51, 51, 51)
                        .addComponent(lblImagen, javax.swing.GroupLayout.PREFERRED_SIZE, 311, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(77, 77, 77)
                .addComponent(btn_carrito)
                .addContainerGap(302, Short.MAX_VALUE))
        );

        Pestañas.addTab("pantalones", pantalones);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1900, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 919, Short.MAX_VALUE)
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
            .addGap(0, 919, Short.MAX_VALUE)
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
            .addGap(0, 919, Short.MAX_VALUE)
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
            .addGap(0, 919, Short.MAX_VALUE)
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

    private void btn_poloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_poloActionPerformed
        // TODO add your handling code here:
        Pestañas.setSelectedIndex(2);
        
        
        
        
    }//GEN-LAST:event_btn_poloActionPerformed

    private void btn_pantalonesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_pantalonesActionPerformed
        // TODO add your handling code here:
        //cargarGaleriaVenta("pantalones", venta);
        Pestañas.setSelectedIndex(2); 
        
       
        
    
    }//GEN-LAST:event_btn_pantalonesActionPerformed

    private void btn_salirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_salirActionPerformed
        // TODO add your handling code here:
        Pestañas.setSelectedIndex(1);
    }//GEN-LAST:event_btn_salirActionPerformed

    private void btn_atras2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_atras2ActionPerformed
        // TODO add your handling code here:
        Pestañas.setSelectedIndex(2);
    }//GEN-LAST:event_btn_atras2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Deportivo;
    private javax.swing.JPanel Galeria;
    private javax.swing.JPanel Inicio;
    private javax.swing.JPanel Panelpuntos;
    private javax.swing.JTabbedPane Pestañas;
    private javax.swing.JButton btn_Atras;
    private javax.swing.JButton btn_Venta;
    private javax.swing.JButton btn_atras;
    private javax.swing.JButton btn_atras2;
    private javax.swing.JButton btn_carrito;
    private javax.swing.JButton btn_casacas;
    private javax.swing.JButton btn_consultas;
    private javax.swing.JButton btn_generar;
    private javax.swing.JButton btn_pantalones;
    private javax.swing.JButton btn_polo;
    private javax.swing.JButton btn_reporte;
    private javax.swing.JButton btn_salir;
    private javax.swing.JButton btn_siguiente;
    private javax.swing.JLabel haber;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
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
    private javax.swing.JPanel pantalones;
    private javax.swing.JPanel puntitos;
    private javax.swing.JPanel venta;
    // End of variables declaration//GEN-END:variables
}
