/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * CatalogoArticulos.java
 *
 * Created on 17/08/2008, 05:43:22 PM
 */

package omoikane.formularios;


import java.sql.*;
import java.util.*;
import javax.swing.table.*;
import javax.swing.*;
import java.awt.image.*;
import java.awt.*;
import java.awt.event.*;
import omoikane.sistema.*;

/**
 *
 * @author Octavio
 */
public class CatalogoArticulos extends javax.swing.JInternalFrame {

    TimerBusqueda          timerBusqueda;
    BufferedImage          fondo;
    public int             IDSeleccionado;
    public String          codigoSeleccionado;
    public int IDAlmacen = omoikane.principal.Principal.IDAlmacen;
    public String          txtQuery;
    
    class TimerBusqueda extends Thread
    {
        CatalogoArticulos ca;
        boolean busquedaActiva = true;

        TimerBusqueda(CatalogoArticulos ca) { this.ca = ca; }
        public void run()
        {
            synchronized(this)
            {
                busquedaActiva = true;
                try { this.wait(600); } catch(Exception e) { Dialogos.lanzarDialogoError(null, "Error en el timer de búsqueda automática", Herramientas.getStackTraceString(e)); }
                if(busquedaActiva) { ca.buscar(); }
            }
        }
        void cancelar()
        {
            busquedaActiva = false;
            try { this.notify(); } catch(Exception e) {}
        }
    }

    public void actionPerformed(ActionEvent ae)
    {
        
    }
    /** Creates new form CatalogoArticulos */
    public CatalogoArticulos() {
        initComponents();
        setQueryTable("select articulos.id_articulo as xID,articulos.codigo as xCodigo,lineas.descripcion as xLinea,articulos.descripcion as xDescripcion,articulos.unidad as xUnidad,precios.costo as xCosto,existencias.cantidad as xExistencias " +
                "from articulos, precios, existencias, lineas " +
                "where articulos.id_articulo=precios.id_articulo and precios.id_almacen = "+IDAlmacen+" AND existencias.id_almacen = "+IDAlmacen+" AND existencias.id_articulo = articulos.id_articulo " +
                "AND lineas.id_linea = articulos.id_linea");

        //Instrucciones para el funcionamiento del fondo semistransparente
        this.setOpaque(false);
        ((JPanel)this.getContentPane()).setOpaque(false);
        this.getLayeredPane().setOpaque(false);
        this.getRootPane().setOpaque(false);
        this.generarFondo(this);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension labelSize = this.getPreferredSize();

        //setLocation(screenSize.width/2 - (labelSize.width/2), screenSize.height/2 - (labelSize.height/2));
        Herramientas.centrarVentana(this);
        this.btnAceptar.setVisible(false);

        //Instrucciones para el funcionamiento de las teclas de navegación
        Set newKeys = new HashSet(getFocusTraversalKeys(KeyboardFocusManager.BACKWARD_TRAVERSAL_KEYS));
        newKeys.add(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0));
        setFocusTraversalKeys(KeyboardFocusManager.BACKWARD_TRAVERSAL_KEYS, newKeys);

        newKeys = new HashSet(getFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS));
        newKeys.add(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0));
        setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, newKeys);


        //La larga asignación de teclas a acciones (presionar F4 para ver detalles por ejemplo), fue lo más
        //  corta que pude hacerla v_v

        Action cerrar = new AbstractAction() { public void actionPerformed(ActionEvent e) {
            ((CatalogoArticulos)e.getSource()).btnCerrar.doClick();
        } };
        Action detalles = new AbstractAction() { public void actionPerformed(ActionEvent e) {
            ((CatalogoArticulos)e.getSource()).btnDetalles.doClick();
        } };
        Action nuevo = new AbstractAction() { public void actionPerformed(ActionEvent e) {
            ((CatalogoArticulos)e.getSource()).btnNuevo.doClick();
        } };
        Action modificar = new AbstractAction() { public void actionPerformed(ActionEvent e) {
            ((CatalogoArticulos)e.getSource()).btnModificar.doClick();
        } };
        Action eliminar = new AbstractAction() { public void actionPerformed(ActionEvent e) {
            ((CatalogoArticulos)e.getSource()).btnEliminar.doClick();
        } };

        getInputMap(JPanel.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "cerrar");
        getActionMap().put("cerrar"                 , cerrar  );
        getInputMap(JPanel.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_F4, 0), "detalles");
        getActionMap().put("detalles"                 , detalles  );
        getInputMap(JPanel.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_F5, 0), "nuevo");
        getActionMap().put("nuevo"                 , nuevo  );
        getInputMap(JPanel.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_F6, 0), "modificar");
        getActionMap().put("modificar"                 , modificar  );
        getInputMap(JPanel.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0), "eliminar");
        getActionMap().put("eliminar"                 , eliminar  );

    }
    public void setModoDialogo()
    {
        this.btnAceptar.setVisible(true);
        Action aceptar = new AbstractAction() { public void actionPerformed(ActionEvent e) {
            ((CatalogoArticulos)e.getSource()).btnAceptar.doClick();
        } };
        getInputMap(JPanel.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "aceptar");
        getActionMap().put("aceptar"                 , aceptar  );
    }

    public void setQueryTable(String query)
    {
        Statement control = null;
        String driver;
        String protocol;
        Connection conn;
        txtQuery = query;

        try
        {
            driver   = "com.mysql.jdbc.Driver";
            protocol = "jdbc:mysql://localhost/omoikane?user=root&password=";
            Class.forName(driver).newInstance();

            conn = DriverManager.getConnection(protocol);

            conn.setAutoCommit(false);
            control = conn.createStatement();

            String[]  columnas = {"Código", "Línea", "Concepto", "Unidad", "Precio", "Existencias"};
            ArrayList cols     = new ArrayList<String>(Arrays.asList(columnas));
            Class[]   clases   = {String.class, String.class, String.class, String.class, Double.class, Double.class};
            ArrayList cls      = new ArrayList<Class>(Arrays.asList(clases));

            ResultSet rs                     = control.executeQuery(query);
            ScrollableTableModel modeloTabla = new ScrollableTableModel(rs,cols, cls);
            this.jTable1.setModel(modeloTabla);
        }
        catch (Exception e) { omoikane.sistema.Dialogos.lanzarDialogoError(null, "Error al conectar a MySQL", omoikane.sistema.Herramientas.getStackTraceString(e)); }

    }
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        txtBusqueda = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btnCerrar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnNuevo = new javax.swing.JButton();
        btnDetalles = new javax.swing.JButton();
        btnImprimir = new javax.swing.JButton();
        btnAceptar = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        chkCodigo = new javax.swing.JCheckBox();
        chkLineas = new javax.swing.JCheckBox();
        chkGrupos = new javax.swing.JCheckBox();

        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
        });

        jScrollPane1.setAutoscrolls(true);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTable1.setRowHeight(12);
        jTable1.setShowHorizontalLines(false);
        jScrollPane1.setViewportView(jTable1);

        txtBusqueda.setNextFocusableComponent(btnDetalles);
        txtBusqueda.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtBusquedaKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBusquedaKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtBusquedaKeyTyped(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Buscar [F3]:");

        jLabel2.setFont(new java.awt.Font("Arial", 1, 36));
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Catálogo de Artículos");

        btnCerrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/omoikane/media/64x64/back.png"))); // NOI18N
        btnCerrar.setText("Cerrar [Esc]");
        btnCerrar.setNextFocusableComponent(txtBusqueda);
        btnCerrar.setRequestFocusEnabled(false);
        btnCerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarActionPerformed(evt);
            }
        });

        btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/omoikane/media/32x32/page_remove.png"))); // NOI18N
        btnEliminar.setText("Eliminar [Supr]");
        btnEliminar.setNextFocusableComponent(btnCerrar);
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        btnModificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/omoikane/media/32x32/page_edit.png"))); // NOI18N
        btnModificar.setText("Modificar [F6]");
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });

        btnNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/omoikane/media/32x32/page_add.png"))); // NOI18N
        btnNuevo.setText("Nuevo [F5]");
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });

        btnDetalles.setIcon(new javax.swing.ImageIcon(getClass().getResource("/omoikane/media/32x32/page_search.png"))); // NOI18N
        btnDetalles.setText("Detalles [F4]");
        btnDetalles.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDetallesActionPerformed(evt);
            }
        });

        btnImprimir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/omoikane/media/32x32/printer.png"))); // NOI18N
        btnImprimir.setText("<html><center>Imprimir [F8]</center></html>");
        btnImprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImprimirActionPerformed(evt);
            }
        });

        btnAceptar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/omoikane/media/32x32/accept.png"))); // NOI18N
        btnAceptar.setText("Aceptar [Enter]");
        btnAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptarActionPerformed(evt);
            }
        });

        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Critério:");

        chkCodigo.setForeground(new java.awt.Color(255, 255, 255));
        chkCodigo.setSelected(true);
        chkCodigo.setText("Cód, Desc");
        chkCodigo.setOpaque(false);
        chkCodigo.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                chkCodigoStateChanged(evt);
            }
        });
        chkCodigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkCodigoActionPerformed(evt);
            }
        });

        chkLineas.setForeground(new java.awt.Color(255, 255, 255));
        chkLineas.setText("Líneas");
        chkLineas.setOpaque(false);
        chkLineas.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                chkLineasStateChanged(evt);
            }
        });

        chkGrupos.setForeground(new java.awt.Color(255, 255, 255));
        chkGrupos.setText("Grupos");
        chkGrupos.setOpaque(false);
        chkGrupos.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                chkGruposStateChanged(evt);
            }
        });
        chkGrupos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkGruposActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 928, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 557, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(6, 6, 6))
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(chkCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(chkLineas, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(chkGrupos, javax.swing.GroupLayout.DEFAULT_SIZE, 73, Short.MAX_VALUE))
                            .addComponent(btnCerrar, javax.swing.GroupLayout.DEFAULT_SIZE, 278, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnAceptar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnDetalles)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnNuevo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnModificar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEliminar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnImprimir, javax.swing.GroupLayout.DEFAULT_SIZE, 231, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(btnCerrar, 0, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel6)
                    .addComponent(txtBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chkCodigo)
                    .addComponent(chkLineas)
                    .addComponent(chkGrupos))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnDetalles)
                    .addComponent(btnNuevo)
                    .addComponent(btnModificar)
                    .addComponent(btnEliminar)
                    .addComponent(btnAceptar)
                    .addComponent(btnImprimir))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents



    private void txtBusquedaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBusquedaKeyTyped
        // TODO add your handling code here:
        preBuscar();
    }//GEN-LAST:event_txtBusquedaKeyTyped

    private void btnCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarActionPerformed
        // TODO add your handling code here:
        this.dispose();
}//GEN-LAST:event_btnCerrarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        // TODO add your handling code here:
        int IDArticulo = ((ScrollableTableModel)jTable1.getModel()).getIDArticuloFila(this.jTable1.getSelectedRow());
        if(IDArticulo != -1) {
            String descripcion = ((ScrollableTableModel)jTable1.getModel()).getDescripcion(jTable1.getSelectedRow());
            if(JOptionPane.showConfirmDialog(null, "¿Realmente desea eliminar éste artículo: \""+descripcion+"\"?", "lala", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
                omoikane.principal.Articulos.eliminarArticulo(IDArticulo);
            }
        }
}//GEN-LAST:event_btnEliminarActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        // TODO add your handling code here:
        int IDArticulo = ((ScrollableTableModel)jTable1.getModel()).getIDArticuloFila(this.jTable1.getSelectedRow());

        //Lanzar la ventana de detalles:
        if(IDArticulo != -1) { omoikane.principal.Articulos.lanzarModificarArticulo(IDArticulo); }
}//GEN-LAST:event_btnModificarActionPerformed

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        // TODO add your handling code here:
        omoikane.principal.Articulos.lanzarFormNuevoArticulo();
}//GEN-LAST:event_btnNuevoActionPerformed

    private void btnDetallesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDetallesActionPerformed
        // TODO add your handling code here:
        int IDArticulo = ((ScrollableTableModel)jTable1.getModel()).getIDArticuloFila(this.jTable1.getSelectedRow());
        
        //Lanzar la ventana de detalles:
        if(IDArticulo != -1) { omoikane.principal.Articulos.lanzarDetallesArticulo(IDArticulo); }
}//GEN-LAST:event_btnDetallesActionPerformed

    private void txtBusquedaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBusquedaKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode() == evt.VK_DOWN)
        {
            int sigFila = jTable1.getSelectedRow()+1;
            if(sigFila < jTable1.getRowCount())
            {
                this.jTable1.setRowSelectionInterval(sigFila, sigFila);
                this.jTable1.scrollRectToVisible(jTable1.getCellRect(sigFila, 1, true));
            }
        }
        if(evt.getKeyCode() == evt.VK_UP)
        {
            int antFila = jTable1.getSelectedRow()-1;
            if(antFila >= 0) {
                this.jTable1.setRowSelectionInterval(antFila, antFila);
                this.jTable1.scrollRectToVisible(jTable1.getCellRect(antFila, 1, true));
            }
        }
        if(evt.getKeyCode() == evt.VK_PAGE_DOWN)
        {
            int nFilas  = (int) this.jScrollPane1.getViewportBorderBounds().getHeight() / jTable1.getRowHeight();
            int sigFila = jTable1.getSelectedRow()+nFilas;
            if(sigFila > jTable1.getRowCount()) {
                sigFila = jTable1.getRowCount()-1;
            }
            if(sigFila < jTable1.getRowCount()) {
                this.jTable1.setRowSelectionInterval(sigFila, sigFila);
                this.jTable1.scrollRectToVisible(jTable1.getCellRect(sigFila, 1, true));
            }
        }
        if(evt.getKeyCode() == evt.VK_PAGE_UP)
        {
            int nFilas  = (int) this.jScrollPane1.getViewportBorderBounds().getHeight() / jTable1.getRowHeight();
            int antFila = jTable1.getSelectedRow()-nFilas;
            if(antFila < 0) {
                antFila = 0;
            }
            this.jTable1.setRowSelectionInterval(antFila, antFila);
            this.jTable1.scrollRectToVisible(jTable1.getCellRect(antFila, 1, true));
        }
        if(evt.getKeyCode() == evt.VK_DELETE)
        {
            this.btnEliminar.doClick();
        }
    }//GEN-LAST:event_txtBusquedaKeyPressed

    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_formKeyPressed

    private void txtBusquedaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBusquedaKeyReleased
        // TODO add your handling code here:
        if(evt.getKeyCode() == evt.VK_ESCAPE) { this.btnCerrar.doClick(); }
    }//GEN-LAST:event_txtBusquedaKeyReleased

    private void btnImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImprimirActionPerformed
        // TODO add your handling code here:
        omoikane.principal.Articulos.lanzarImprimir(this);
    }//GEN-LAST:event_btnImprimirActionPerformed

    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed
        // TODO add your handling code here:
        ScrollableTableModel stm = ((ScrollableTableModel)jTable1.getModel());
        int IDArticulo = stm.getIDArticuloFila(this.jTable1.getSelectedRow());

        if(IDArticulo != -1) { 
            IDSeleccionado = IDArticulo; codigoSeleccionado = (String)stm.getValueAt(this.jTable1.getSelectedRow(), 0);
            this.btnCerrar.doClick();
        }
}//GEN-LAST:event_btnAceptarActionPerformed

    private void chkCodigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkCodigoActionPerformed
        // TODO add your handling code here:
}//GEN-LAST:event_chkCodigoActionPerformed

    private void chkCodigoStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_chkCodigoStateChanged
        // TODO add your handling code here:
        buscar();
    }//GEN-LAST:event_chkCodigoStateChanged

    private void chkLineasStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_chkLineasStateChanged
        // TODO add your handling code here:
        buscar();
    }//GEN-LAST:event_chkLineasStateChanged

    private void chkGruposStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_chkGruposStateChanged
        // TODO add your handling code here:
        omoikane.sistema.Dialogos.lanzarAlerta("Grupos aún no implementados!!");
    }//GEN-LAST:event_chkGruposStateChanged

    private void chkGruposActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkGruposActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chkGruposActionPerformed

    public boolean getBuscarCodigoDescripcion()
    {
        return this.chkCodigo.getModel().isSelected();
    }
    public boolean getBuscarLineas() {
        return this.chkLineas.getModel().isSelected();
    }
    public boolean getBuscarGrupos() {
        return false;
    }
    public void preBuscar()
    {
        if(timerBusqueda != null && timerBusqueda.isAlive()) { timerBusqueda.cancelar(); }
        this.timerBusqueda = new TimerBusqueda(this);
        timerBusqueda.start();
       
    }

    public void buscar()
    {
        boolean xCodDes = getBuscarCodigoDescripcion();
        boolean xLineas = getBuscarLineas();
        boolean xGrupos = getBuscarGrupos();
        String busqueda = this.txtBusqueda.getText();
        String query    = "select articulos.id_articulo as xID,articulos.codigo as xCodigo,lineas.descripcion as xLinea,articulos.descripcion as xDescripcion,articulos.unidad as xUnidad,precios.costo as xCosto,existencias.cantidad as xExistencias " +
                "from articulos, precios, existencias, lineas " +
                "WHERE precios.id_almacen="+IDAlmacen+" AND existencias.id_almacen="+IDAlmacen+" AND existencias.id_articulo=articulos.id_articulo AND articulos.id_linea = lineas.id_linea AND articulos.id_articulo = precios.id_articulo ";
        if(xCodDes || xLineas || xGrupos) { query += "AND ("; }
        if(xCodDes) {
                query += "(articulos.descripcion like '%"+busqueda+"%' or articulos.codigo like '%"+busqueda+"%') ";
        }
        if(xCodDes && (xLineas || xGrupos)) { query += "OR "; }
        if(xLineas) {
                query += "(lineas.descripcion like '%"+busqueda+"%' or lineas.id_linea like '%"+busqueda+"%') ";
        }
        if((xLineas||xCodDes) && xGrupos) { query += "OR "; }
        if(xGrupos) {
                query += "(grupos.descripcion like '%"+busqueda+"%' or grupos.id_grupo like '%"+busqueda+"%') ";
        }
        if(xCodDes || xLineas || xGrupos) { query += ")"; }

        
        setQueryTable(query);
    }


    public void paintComponent(Graphics g)
    {
      Graphics2D g2d = (Graphics2D) g;
      g2d.drawImage(fondo, 0, 0, null);

    }
    public void generarFondo(Component componente)
    {
      Rectangle areaDibujo = this.getBounds();
      BufferedImage tmp;
      GraphicsConfiguration gc = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration();

      tmp = gc.createCompatibleImage(areaDibujo.width, areaDibujo.height,BufferedImage.TRANSLUCENT);
      Graphics2D g2d = (Graphics2D) tmp.getGraphics();
      g2d.setColor(new Color(55,55,255,165));
      g2d.fillRect(0,0,areaDibujo.width,areaDibujo.height);
      fondo = tmp;
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAceptar;
    private javax.swing.JButton btnCerrar;
    private javax.swing.JButton btnDetalles;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnImprimir;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JCheckBox chkCodigo;
    private javax.swing.JCheckBox chkGrupos;
    private javax.swing.JCheckBox chkLineas;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    public javax.swing.JTextField txtBusqueda;
    // End of variables declaration//GEN-END:variables

}



/////////////////////////////////////////////////
/////////////////////////////////////////////////
//Modelo para esta tabla
/////////////////////////////////////////////////
/////////////////////////////////////////////////

class ScrollableTableModel extends AbstractTableModel {
	ResultSet	resultSet	= null;
	Connection	connection	= null;
	java.util.List	colNames	= null;
	int             rowCount        = -1;
	java.util.List  colClasses      = null;
	Statement       stmt            = null;

	/**
	 * <p>A constructor with a scrollable
	 * <code>java.sql.ResultSet</code> as parameter.</p>
	 * @param rs The scrollable <code>java.sql.ResultSet</code>
	 * @param colNames A <code>java.util.List</code> containing
	 * the column names
	 */
	public ScrollableTableModel(ResultSet rs, java.util.List colNames, ArrayList colClases) {

		if (rs == null) {
                        omoikane.sistema.Dialogos.lanzarDialogoError(null, "La consulta es nula al obtener listado", "");
		}
		Statement s = null;
		try {
			s = rs.getStatement();
		} catch (SQLException e) {
                        omoikane.sistema.Dialogos.lanzarDialogoError(null, "Error obteniendo declaración de la consulta", omoikane.sistema.Herramientas.getStackTraceString(e));
			}
		Connection c = null;
		try {
			c = s.getConnection();
		} catch (SQLException e) {
                        omoikane.sistema.Dialogos.lanzarDialogoError(null, "Error obteniendo conexión de la consulta", omoikane.sistema.Herramientas.getStackTraceString(e));
                }
		if (!supportsScrollInsensitive(c)) {
                        omoikane.sistema.Dialogos.lanzarDialogoError(null, "Error: La base de datos no soporta insensitive scroll", "");
		}

                this.colNames   = new ArrayList(colNames);
                this.colClasses = colClases;

		this.resultSet = rs;
		this.stmt = s;
	}

	/**
	 * <p>Fills the <code>colNames</code> property from the
	 * <code>resultSet</code> if this property is null
	 * using the current <code>resultSet</code>.</p>
	 * @see ScrollableTableModel#fillColNames(ResultSet)
	 */
	/**
	 * @return The number of columns in this model.
	 */
	public int getColumnCount() {
		return colNames.size();
	}

	/**
	 * @return The number of rows in this model.
	 */
	public int getRowCount() {
		if (this.rowCount == -1) {
			try {
				resultSet.last();
				this.rowCount = resultSet.getRow();
			} catch (SQLException e) {
                                omoikane.sistema.Dialogos.lanzarDialogoError(null, "Error al pasar a la última fila", omoikane.sistema.Herramientas.getStackTraceString(e));
                        }
		}

		return rowCount;
	}

	/**
	 * <p>Returns the value for the cell at columnIndex
	 * and rowIndex</p>
	 * @param rowIndex The row whose value is to be queried
	 * @param columnIndex The column whose value is to be queried
	 * @return The value Object at the specified cell
	 */
	public Object getValueAt(int rowIndex, int columnIndex) {
		int rowNdx = rowIndex + 1;
		int colNdx = columnIndex + 1;
		try {
			resultSet.absolute(rowNdx);
			return resultSet.getObject(colNdx+1); //Para saltar el ID y no mostrarlo
		} catch (SQLException e) {
                        omoikane.sistema.Dialogos.lanzarDialogoError(null, "Error obteniendo valor en la celda: " + rowIndex + ", " + columnIndex, omoikane.sistema.Herramientas.getStackTraceString(e));
                        return null;
                }
        }

        public int getIDArticuloFila(int rowIndex)
        {
            //return (Integer)getValueAt(rowIndex, -1);
            
            if(rowIndex == -1)
            { Dialogos.lanzarAlerta("Ningúna fila ha sido seleccionada."); }
            else
            { return (Integer)getValueAt(rowIndex, -1); }
            return -1;
            
        }
        public String getDescripcion(int rowIndex)
        {
            return (String)getValueAt(rowIndex, 1);
        }

	/**
	 * @return The column name
	 */
	public String getColumnName(int column) {
		return (String)colNames.get(column);
	}

	/**
	 * <p>Returns the most specific superclass for all the cell values in the column.</p>
	 * @param columnIndex The index of the column
	 * @return The common ancestor class of the object values in the model.
	 */
	public Class getColumnClass(int columnIndex) {

		Class c = (Class)colClasses.get(columnIndex);

		return c;
	}

	/**
	 * <p>Verifies if the current DBM supports the scroll insensitive feature.</p>
	 * @param con The opened connection.
	 * @return <code>true</code> if the DMB supports the scroll insensitive
	 * feature or <code>false</code> if not.
	 */
	private boolean supportsScrollInsensitive(Connection con) {
		DatabaseMetaData md = null;
		try {
			md = con.getMetaData();
		} catch (SQLException e) {
                        omoikane.sistema.Dialogos.lanzarDialogoError(null, "Error obteniendo metadata", omoikane.sistema.Herramientas.getStackTraceString(e));
                        return false;
		}
		try {
			return md.supportsResultSetType(ResultSet.TYPE_SCROLL_INSENSITIVE);
		} catch (SQLException e) {
                        omoikane.sistema.Dialogos.lanzarDialogoError(null, "Error obteniendo información de metadata", omoikane.sistema.Herramientas.getStackTraceString(e));
                        return false;
		}
	} // supportsScrollInsensitive()

	/**
	 * Closes the <code>java.sql.Statement</code> used to
	 * execute the query.
	 */
	public void destroy() {
		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {
				}
		}
		stmt = null;
	}

}