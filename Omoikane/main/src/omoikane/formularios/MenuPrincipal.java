/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * MenuPrincipal.java
 *
 * Created on 15/08/2008, 11:31:38 AM
 */

package omoikane.formularios;

import javax.swing.*;
import java.awt.image.*;
import java.awt.*;
import java.util.*;
import java.awt.event.*;

import omoikane.caja.CajaManager;
import omoikane.configuracion.ConfiguratorAppManager;
import omoikane.etiquetas.ImpresionEtiquetasManager;
import omoikane.mepro.Mepro;
import omoikane.moduloreportes.MenuOmoikane;
import omoikane.proveedores.ProveedoresManager;
import omoikane.sistema.Herramientas;
import omoikane.sistema.StopWatch;

/**
 *
 * @author Octavio
 */
public class MenuPrincipal extends javax.swing.JInternalFrame {

    BufferedImage fondo;
    /** Creates new form MenuPrincipal */
    public MenuPrincipal() {
        
        initComponents();

        //Instrucciones para el funcionamiento del fondo semistransparente
        this.setOpaque(false);
        ((JPanel)this.getContentPane()).setOpaque(false);
        this.getLayeredPane().setOpaque(false);
        this.getRootPane().setOpaque(false);
        this.generarFondo(this);
        
        //Instrucciones para el funcionamiento de las teclas de navegación
        Set newKeys = new HashSet(getFocusTraversalKeys(KeyboardFocusManager.BACKWARD_TRAVERSAL_KEYS));
        newKeys.add(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0));
        setFocusTraversalKeys(KeyboardFocusManager.BACKWARD_TRAVERSAL_KEYS, newKeys);

        newKeys = new HashSet(getFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS));
        newKeys.add(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0));
        setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, newKeys);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension labelSize = this.getPreferredSize();

    }

    /** This method is called from within the constructor to
     *< initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    // Generated using JFormDesigner Evaluation license - Octavio Ruiz
    private void initComponents() {
        jLabel1 = new JLabel();
        btnCerrar = new JButton();
        btnVender = new JButton();
        jLabel2 = new JLabel();
        btnCajas = new JButton();
        jSeparator2 = new JSeparator();
        jLabel3 = new JLabel();
        jLabel4 = new JLabel();
        jSeparator3 = new JSeparator();
        btnAlmacenes = new JButton();
        btnMovAlmacen = new JButton();
        btnUsuarios = new JButton();
        jSeparator4 = new JSeparator();
        jLabel5 = new JLabel();
        jPanel1 = new JPanel();
        btnLineas = new JButton();
        btnCortes = new JButton();
        btnArticulos = new JButton();
        btnGrupos = new JButton();
        jSeparator5 = new JSeparator();
        btnDetallesVentas = new JButton();
        btnReportes = new JButton();
        btnConfig = new JButton();
        lblVersion = new JLabel();
        btnEtiquetas = new JButton();
        btnMepro    = new JButton();
        btnProveedores = new JButton();

        //======== this ========
        setVisible(true);
        setIconifiable(true);
        setTitle("Men\u00fa Principal");
        setFocusCycleRoot(false);
        addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                formFocusGained(e);
            }
        });
        Container contentPane = getContentPane();
        contentPane.setLayout(null);

        //---- jLabel1 ----
        jLabel1.setFont(new Font("Arial", Font.BOLD, 36));
        jLabel1.setForeground(Color.white);
        jLabel1.setText("Principal");
        contentPane.add(jLabel1);
        jLabel1.setBounds(new Rectangle(new Point(30, 20), jLabel1.getPreferredSize()));

        //---- btnCerrar ----
        btnCerrar.setFont(new Font("Arial", Font.PLAIN, 14));
        btnCerrar.setIcon(new ImageIcon(getClass().getResource("/file-manager-128x128.png")));
        btnCerrar.setText("Cerrar");
        btnCerrar.setIconTextGap(-5);
        btnCerrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnCerrarActionPerformed(e);
            }
        });
        contentPane.add(btnCerrar);
        btnCerrar.setBounds(660, 10, 200, 70);

        //---- btnVender ----
        btnVender.setFont(new Font("Arial", Font.PLAIN, 14));
        btnVender.setIcon(new ImageIcon(getClass().getResource("/package-toys-128x128.png")));
        btnVender.setText("Vender");
        btnVender.setIconTextGap(-5);
        btnVender.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnVenderActionPerformed(e);
            }
        });
        contentPane.add(btnVender);
        btnVender.setBounds(20, 110, 210, 70);

        //----- btnEtiquetas
        btnEtiquetas.setFont(new Font("Arial", Font.PLAIN, 14));
        btnEtiquetas.setIcon(new ImageIcon(getClass().getResource("/128x128/printer.png")));
        btnEtiquetas.setText("Etiquetas");
        btnEtiquetas.setIconTextGap(-20);
        btnEtiquetas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnEtiquetasActionPerformed(e);
            }
        });
        contentPane.add(btnEtiquetas);
        btnEtiquetas.setBounds(660, 210, 200, 70);

        //----- btnMepro
        btnMepro.setFont(new Font("Arial", Font.PLAIN, 14));
        btnMepro.setIcon(new ImageIcon(getClass().getResource("/128x128/computer.png")));
        btnMepro.setText("Scripting");
        btnMepro.setIconTextGap(-20);
        btnMepro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnMeproActionPerformed(e);
            }
        });
        contentPane.add(btnMepro);
        btnMepro.setBounds(660, 410, 200, 70);

        //---- jLabel2 ----
        jLabel2.setFont(new Font("Arial", Font.PLAIN, 14));
        jLabel2.setForeground(Color.white);
        jLabel2.setText("Art\u00edculos");
        contentPane.add(jLabel2);
        jLabel2.setBounds(new Rectangle(new Point(20, 180), jLabel2.getPreferredSize()));

        //---- btnCajas ----
        btnCajas.setFont(new Font("Arial", Font.PLAIN, 14));
        btnCajas.setIcon(new ImageIcon(getClass().getResource("/download-manager-128x128.png")));
        btnCajas.setText("Cajas");
        btnCajas.setIconTextGap(-5);
        btnCajas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnCajasActionPerformed(e);
            }
        });
        contentPane.add(btnCajas);
        btnCajas.setBounds(450, 310, 200, 70);
        contentPane.add(jSeparator2);
        jSeparator2.setBounds(20, 100, 840, 10);

        //---- jLabel3 ----
        jLabel3.setFont(new Font("Arial", Font.PLAIN, 14));
        jLabel3.setForeground(Color.white);
        jLabel3.setText("Ventas");
        contentPane.add(jLabel3);
        jLabel3.setBounds(new Rectangle(new Point(20, 80), jLabel3.getPreferredSize()));

        //---- jLabel4 ----
        jLabel4.setFont(new Font("Arial", Font.PLAIN, 14));
        jLabel4.setForeground(Color.white);
        jLabel4.setText("Almacen");
        contentPane.add(jLabel4);
        jLabel4.setBounds(new Rectangle(new Point(20, 280), jLabel4.getPreferredSize()));
        contentPane.add(jSeparator3);
        jSeparator3.setBounds(20, 200, 840, 10);

        //---- btnAlmacenes ----
        btnAlmacenes.setFont(new Font("Arial", Font.PLAIN, 14));
        btnAlmacenes.setIcon(new ImageIcon(getClass().getResource("/server-128x128.png")));
        btnAlmacenes.setText("Almacenes");
        btnAlmacenes.setIconTextGap(-30);
        btnAlmacenes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnAlmacenesActionPerformed(e);
            }
        });
        contentPane.add(btnAlmacenes);
        btnAlmacenes.setBounds(20, 310, 210, 70);

        //---- btnMovAlmacen ----
        btnMovAlmacen.setFont(new Font("Arial", Font.PLAIN, 14));
        btnMovAlmacen.setIcon(new ImageIcon(getClass().getResource("/k-black-box-128x128.png")));
        btnMovAlmacen.setText("<html>Movimientos<br>Almac\u00e9n</html>");
        btnMovAlmacen.setIconTextGap(-25);
        btnMovAlmacen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnMovAlmacenActionPerformed(e);
            }
        });
        contentPane.add(btnMovAlmacen);
        btnMovAlmacen.setBounds(240, 310, 200, 70);

        //---- btnUsuarios ----
        btnUsuarios.setFont(new Font("Arial", Font.PLAIN, 14));
        btnUsuarios.setIcon(new ImageIcon(getClass().getResource("/proxy-128x128.png")));
        btnUsuarios.setText("Usuarios");
        //btnUsuarios.setHorizontalTextPosition(SwingConstants.CENTER);
        btnUsuarios.setIconTextGap(-10);
        //btnUsuarios.setVerticalAlignment(SwingConstants.TOP);
        //btnUsuarios.setVerticalTextPosition(SwingConstants.TOP);
        btnUsuarios.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnUsuariosActionPerformed(e);
            }
        });
        contentPane.add(btnUsuarios);
        btnUsuarios.setBounds(20, 410, 210, 70);
        contentPane.add(jSeparator4);
        jSeparator4.setBounds(20, 400, 840, 10);

        //---- jLabel5 ----
        jLabel5.setFont(new Font("Arial", Font.PLAIN, 14));
        jLabel5.setForeground(Color.white);
        jLabel5.setText("Administraci\u00f3n");
        contentPane.add(jLabel5);
        jLabel5.setBounds(new Rectangle(new Point(20, 380), jLabel5.getPreferredSize()));

        //======== jPanel1 ========
        {
            jPanel1.setOpaque(false);

            // JFormDesigner evaluation mark
            jPanel1.setBorder(new javax.swing.border.CompoundBorder(
                new javax.swing.border.TitledBorder(new javax.swing.border.EmptyBorder(0, 0, 0, 0),
                    "JFormDesigner Evaluation", javax.swing.border.TitledBorder.CENTER,
                    javax.swing.border.TitledBorder.BOTTOM, new java.awt.Font("Dialog", java.awt.Font.BOLD, 12),
                    java.awt.Color.red), jPanel1.getBorder())); jPanel1.addPropertyChangeListener(new java.beans.PropertyChangeListener(){public void propertyChange(java.beans.PropertyChangeEvent e){if("border".equals(e.getPropertyName()))throw new RuntimeException();}});


            GroupLayout jPanel1Layout = new GroupLayout(jPanel1);
            jPanel1.setLayout(jPanel1Layout);
            jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup()
                    .addGap(0, 25, Short.MAX_VALUE)
            );
            jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup()
                    .addGap(0, 25, Short.MAX_VALUE)
            );
        }
        contentPane.add(jPanel1);
        jPanel1.setBounds(865, 500, 10, 10);

        //---- btnLineas ----
        btnLineas.setFont(new Font("Arial", Font.PLAIN, 14));
        btnLineas.setIcon(new ImageIcon(getClass().getResource("/usb-128x128.png")));
        btnLineas.setText("L\u00edneas");
        btnLineas.setIconTextGap(-5);
        btnLineas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnLineasActionPerformed(e);
            }
        });
        contentPane.add(btnLineas);
        btnLineas.setBounds(240, 210, 200, 70);

        //---- btnCortes ----
        btnCortes.setFont(new Font("Arial", Font.PLAIN, 14));
        btnCortes.setIcon(new ImageIcon(getClass().getResource("/super-karamba-128x128.png")));
        btnCortes.setText("Cortes");
        btnCortes.setIconTextGap(-5);
        btnCortes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnCortesActionPerformed(e);
            }
        });
        contentPane.add(btnCortes);
        btnCortes.setBounds(450, 110, 200, 70);

        //---- btnArticulos ----
        btnArticulos.setFont(new Font("Arial", Font.PLAIN, 14));
        btnArticulos.setIcon(new ImageIcon(getClass().getResource("/x-mag-128x128.png")));
        btnArticulos.setText("Cat\u00e1logo");
        btnArticulos.setIconTextGap(-25);
        btnArticulos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnArticulosActionPerformed(e);
            }
        });
        contentPane.add(btnArticulos);
        btnArticulos.setBounds(20, 210, 210, 70);

        //---- btnGrupos ----
        btnGrupos.setFont(new Font("Arial", Font.PLAIN, 14));
        btnGrupos.setIcon(new ImageIcon(getClass().getResource("/folder-blue-128x128.png")));
        btnGrupos.setText("Grupos");
        btnGrupos.setIconTextGap(-15);
        btnGrupos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnGruposActionPerformed(e);
            }
        });
        contentPane.add(btnGrupos);
        btnGrupos.setBounds(450, 210, 200, 70);
        contentPane.add(jSeparator5);
        jSeparator5.setBounds(20, 300, 840, 10);

        //---- btnDetallesVentas ----
        btnDetallesVentas.setFont(new Font("Arial", Font.PLAIN, 14));
        btnDetallesVentas.setIcon(new ImageIcon(getClass().getResource("/remote-128x128.png")));
        btnDetallesVentas.setText("<html>Detalles<br>de Ventas</html>");
        btnDetallesVentas.setIconTextGap(-5);
        btnDetallesVentas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnDetallesVentasActionPerformed(e);
            }
        });
        contentPane.add(btnDetallesVentas);
        btnDetallesVentas.setBounds(240, 110, 200, 70);

        //---- btnReportes ----
        btnReportes.setFont(new Font("Arial", Font.PLAIN, 14));
        btnReportes.setText("<html>Reportes<br>Avanzados</html>");
        btnReportes.setIcon(new ImageIcon(getClass().getResource("/k-chart-128x128.png")));
        btnReportes.setIconTextGap(-15);
        btnReportes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { btnReportesActionPerformed(e); }
        });
        contentPane.add(btnReportes);
        btnReportes.setBounds(240, 410, 200, 70);

        //---- btnConfig ----
        btnConfig.setFont(new Font("Arial", Font.PLAIN, 14));
        btnConfig.setText("Controles");
        btnConfig.setIcon(new ImageIcon(getClass().getResource("/k-sysguard-128x128.png")));
        btnConfig.setIconTextGap(-17);
        btnConfig.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnConfigActionPerformed(e);
            }
        });
        contentPane.add(btnConfig);
        btnConfig.setBounds(450, 410, 200, 70);

        //---- btnProveedores ----
        btnProveedores.setFont(new Font("Arial", Font.PLAIN, 14));
        btnProveedores.setText("Proveedores");
        btnProveedores.setIcon(new ImageIcon(getClass().getResource("/128x128/community_users.png")));
        btnProveedores.setIconTextGap(-37);
        btnProveedores.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnProveedoresActionPerformed(e);
            }
        });
        contentPane.add(btnProveedores);
        btnProveedores.setBounds(660, 310, 200, 70);

        //---- lblVersion ----
        lblVersion.setHorizontalAlignment(SwingConstants.RIGHT);
        lblVersion.setText("Versi\u00f3n");
        lblVersion.setForeground(Color.blue);
        contentPane.add(lblVersion);
        lblVersion.setBounds(815, 485, 65, lblVersion.getPreferredSize().height);

        { // compute preferred size
            Dimension preferredSize = new Dimension();
            for(int i = 0; i < contentPane.getComponentCount(); i++) {
                Rectangle bounds = contentPane.getComponent(i).getBounds();
                preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
            }
            Insets insets = contentPane.getInsets();
            preferredSize.width += insets.right;
            preferredSize.height += insets.bottom;
            contentPane.setMinimumSize(preferredSize);
            contentPane.setPreferredSize(preferredSize);
        }
    }// </editor-fold>//GEN-END:initComponents

    private void btnCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarActionPerformed
        // TODO add your handling code here:
//        this.dispose();
//        ((omoikane.principal.Escritorio)omoikane.principal.Principal.getEscritorio()).getFrameEscritorio().dispose();
//        System.exit(0);
}//GEN-LAST:event_btnCerrarActionPerformed

    private void btnAlmacenesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlmacenesActionPerformed
        omoikane.principal.Almacenes.lanzarCatalogo();
}//GEN-LAST:event_btnAlmacenesActionPerformed

    private void btnCajasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCajasActionPerformed
        omoikane.principal.Caja.lanzarCatalogo();
}//GEN-LAST:event_btnCajasActionPerformed

    private void btnMovAlmacenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMovAlmacenActionPerformed
        StopWatch timer = new StopWatch().start();
        omoikane.principal.Almacenes.lanzarMovimientos();
        System.out.println(timer.getElapsedTime());
}//GEN-LAST:event_btnMovAlmacenActionPerformed

    private void btnLineasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLineasActionPerformed
        StopWatch timer = new StopWatch().start();
        omoikane.principal.Lineas.lanzarCatalogo();
        System.out.println(timer.getElapsedTime());
}//GEN-LAST:event_btnLineasActionPerformed

    private void btnVenderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVenderActionPerformed
        StopWatch timer = new StopWatch().start();
        //omoikane.principal.Caja.lanzar(); //Caja antigua

        CajaManager manager = new CajaManager();
        JInternalFrame internalFrame = manager.startJFXCaja();

        System.out.println(timer.getElapsedTime());
}//GEN-LAST:event_btnVenderActionPerformed


    private void btnCortesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCortesActionPerformed
        StopWatch timer = new StopWatch().start();
        omoikane.principal.Cortes.lanzarCatalogoSuc();
        System.out.println(timer.getElapsedTime());
}//GEN-LAST:event_btnCortesActionPerformed

    private void btnArticulosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnArticulosActionPerformed
        StopWatch timer = new StopWatch().start();
        omoikane.principal.Articulos.lanzarCatalogo();
        System.out.println(timer.getElapsedTime());
}//GEN-LAST:event_btnArticulosActionPerformed

    private void btnClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClientesActionPerformed

        StopWatch timer = new StopWatch().start();
        omoikane.principal.Clientes.lanzarCatalogo();
        System.out.println(timer.getElapsedTime());
}//GEN-LAST:event_btnClientesActionPerformed

    private void btnUsuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUsuariosActionPerformed
        omoikane.principal.Usuarios.lanzarCatalogo();
}//GEN-LAST:event_btnUsuariosActionPerformed

    private void btnGruposActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGruposActionPerformed
        StopWatch timer = new StopWatch().start();
        omoikane.principal.Grupos.lanzarCatalogo();
        System.out.println(timer.getElapsedTime());
}//GEN-LAST:event_btnGruposActionPerformed

    private void formFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_formFocusGained
        this.btnVender.requestFocusInWindow();
}//GEN-LAST:event_formFocusGained

    private void btnPreferenciasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPreferenciasActionPerformed
        // TODO Cambiar esto por preferencias
       omoikane.principal.LineasDuales.lanzarCatalogo();

    }//GEN-LAST:event_btnPreferenciasActionPerformed

    private void btnDetallesVentasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDetallesVentasActionPerformed
        // TODO add your handling code here:
        StopWatch timer = new StopWatch().start();
        omoikane.principal.Ventas.lanzarCatalogo();
        System.out.println(timer.getElapsedTime());
    }//GEN-LAST:event_btnDetallesVentasActionPerformed

    private void btnFacturas1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFacturas1ActionPerformed
        // TODO add your handling code here:
        StopWatch timer = new StopWatch().start();
        omoikane.principal.Facturas.lanzarListadoFacturas();
        System.out.println(timer.getElapsedTime());
    }//GEN-LAST:event_btnFacturas1ActionPerformed

    private void btnReportesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        //new omoikane.menudinamico.MenuModulos();
        MenuOmoikane menuOmoikane = new MenuOmoikane();
        menuOmoikane.launch();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnConfigActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        //new omoikane.menudinamico.MenuModulos();
        ConfiguratorAppManager manager = new ConfiguratorAppManager();
        JInternalFrame frame = manager.startJFXConfigurator();
    }

    private void btnEtiquetasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        ImpresionEtiquetasManager manager = new ImpresionEtiquetasManager();
        JInternalFrame internalFrame = manager.startJFXEtiqueta();

    }

    private void btnMeproActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        Mepro mepro = new Mepro();
        JFrame frameMepro = (JFrame) mepro.getMainMenu();
        frameMepro.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    private void btnProveedoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        ProveedoresManager pm = new ProveedoresManager();
        JInternalFrame frame = pm.startJFXProveedores();
    }

    @Override
    public void paintComponent(Graphics g)
    {
      Graphics2D g2d = (Graphics2D) g;
      g2d.drawImage(fondo, 0, 0, null);

    }

    public void generarFondo(Component componente)
    {
      Rectangle areaDibujo = new Rectangle(this.getPreferredSize());
      BufferedImage tmp;
      GraphicsConfiguration gc = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration();

      tmp = gc.createCompatibleImage(areaDibujo.width, areaDibujo.height,BufferedImage.TRANSLUCENT);
      Graphics2D g2d = (Graphics2D) tmp.getGraphics();
      g2d.setColor(new Color(55,55,255,165));
      g2d.fillRect(0,0,areaDibujo.width,areaDibujo.height);
      fondo = tmp;

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - Octavio Ruiz
    private JLabel jLabel1;
    private JButton btnCerrar;
    private JButton btnVender;
    private JLabel jLabel2;
    private JButton btnCajas;
    private JSeparator jSeparator2;
    private JLabel jLabel3;
    private JLabel jLabel4;
    private JSeparator jSeparator3;
    private JButton btnAlmacenes;
    private JButton btnMovAlmacen;
    private JButton btnUsuarios;
    private JSeparator jSeparator4;
    private JLabel jLabel5;
    private JPanel jPanel1;
    private JButton btnLineas;
    private JButton btnCortes;
    private JButton btnArticulos;
    private JButton btnGrupos;
    private JSeparator jSeparator5;
    private JButton btnDetallesVentas;
    private JButton btnReportes;
    private JLabel lblVersion;
    private JButton btnConfig;
    private JButton btnEtiquetas;
    private JButton btnMepro;
    private JButton btnProveedores;

    // End of variables declaration//GEN-END:variables

}
