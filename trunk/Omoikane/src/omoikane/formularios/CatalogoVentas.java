/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * CatalogoVentas.java
 *
 * Created on 10/12/2008, 11:56:06 PM
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
import java.text.*;
import java.util.Calendar;

public class CatalogoVentas extends javax.swing.JInternalFrame {

    //TimerBusqueda          timerBusqueda;
    TimerBusqueda          timerBusqueda;
    public int IDAlmacen = omoikane.principal.Principal.IDAlmacen;
    BufferedImage          fondo;
    public int             IDSeleccionado;
    public String          codigoSeleccionado;
    public String          txtQuery;
    omoikane.sistema.NadesicoTableModel modelo;

    class TimerBusqueda extends Thread
    {
        CatalogoVentas ca;
        boolean busquedaActiva = true;

        TimerBusqueda(CatalogoVentas ca) { this.ca = ca; }
        public void run()
        {
            synchronized(this)
            {
                busquedaActiva = true;
                try { this.wait(1000); } catch(Exception e) { Dialogos.lanzarDialogoError(null, "Error en el timer de búsqueda automática", Herramientas.getStackTraceString(e)); }
                if(busquedaActiva) { ca.buscar(); }
            }
        }
        void cancelar()
        {
            busquedaActiva = false;
            try { this.notify(); } catch(Exception e) {}
        }
    }
    /** Creates new form CatalogoVentas */
    public CatalogoVentas() {
        initComponents();
        Calendar calendario = Calendar.getInstance();
        txtFechaHasta.setDate(calendario.getTime());
        calendario.add(Calendar.DAY_OF_MONTH, -30);
         txtFechaDesde.setDate(calendario.getTime());

        String[]  columnas = {"Venta", "Caja", "Almacen", "Cliente", "Fecha","Total"};
        ArrayList cols     = new ArrayList<String>(Arrays.asList(columnas));
        Class[]   clases   = {Integer.class, Integer.class, Integer.class, Integer.class, String.class, Double.class};
        ArrayList cls      = new ArrayList<Class>(Arrays.asList(clases));

        NadesicoTableModel modeloTabla = new NadesicoTableModel(cols, cls);
        //jTable1.enableInputMethods(false);
        this.modelo = modeloTabla;
        this.jTable1.setModel(modeloTabla);

        setQueryTable("SELECT id_venta,id_venta,id_caja,id_almacen,id_cliente,fecha_hora,total FROM ventas WHERE id_almacen="+IDAlmacen+" ");

        //Instrucciones para el funcionamiento del fondo semistransparente
        this.setOpaque(false);
        ((JPanel)this.getContentPane()).setOpaque(false);
        this.getLayeredPane().setOpaque(false);
        this.getRootPane().setOpaque(false);
        this.generarFondo(this);

        Herramientas.centrarVentana(this);
        this.btnAceptar.setVisible(false);

        //Instrucciones para el funcionamiento de las teclas de navegación
        Set newKeys = new HashSet(getFocusTraversalKeys(KeyboardFocusManager.BACKWARD_TRAVERSAL_KEYS));
        newKeys.add(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0));
        setFocusTraversalKeys(KeyboardFocusManager.BACKWARD_TRAVERSAL_KEYS, newKeys);

        newKeys = new HashSet(getFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS));
        newKeys.add(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0));
        setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, newKeys);

    }

    public void setQueryTable(String query) {
        txtQuery = query;

        modelo.setQuery(query);

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        txtBusqueda = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        btnFiltrar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        btnCerrar = new javax.swing.JButton();
        txtFechaDesde = new org.jdesktop.swingx.JXDatePicker();
        txtFechaHasta = new org.jdesktop.swingx.JXDatePicker();
        chkFecha = new javax.swing.JCheckBox();
        chkUsuario = new javax.swing.JCheckBox();
        chkCaja = new javax.swing.JCheckBox();
        btnAceptar = new javax.swing.JButton();
        btnDetalles = new javax.swing.JButton();
        btnImprimir = new javax.swing.JButton();

        jLabel2.setFont(new java.awt.Font("Arial", 1, 36));
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Catalogo Ventas");

        txtBusqueda.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtBusquedaKeyTyped(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Buscar [F3]:");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Desde:");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Hasta:");

        btnFiltrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/omoikane/media/16x16/search.png"))); // NOI18N
        btnFiltrar.setText("Filtrar [Enter]");
        btnFiltrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFiltrarActionPerformed(evt);
            }
        });

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
        jScrollPane1.setViewportView(jTable1);

        btnCerrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/omoikane/media/64x64/back.png"))); // NOI18N
        btnCerrar.setText("<HTML>Regresar a menú [Esc]</HTML>");
        btnCerrar.setRequestFocusEnabled(false);
        btnCerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarActionPerformed(evt);
            }
        });

        chkFecha.setText("Fecha");
        chkFecha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkFechaActionPerformed(evt);
            }
        });

        chkUsuario.setSelected(true);
        chkUsuario.setText("Usuario");

        chkCaja.setText("Caja");

        btnAceptar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/omoikane/media/32x32/accept.png"))); // NOI18N
        btnAceptar.setText("Aceptar [Enter]");
        btnAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptarActionPerformed(evt);
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 886, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 374, Short.MAX_VALUE)
                                .addComponent(btnCerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(8, 8, 8)
                                .addComponent(txtBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtFechaDesde, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(16, 16, 16)
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(txtFechaHasta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(btnFiltrar, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(chkUsuario))))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(chkFecha)
                        .addGap(180, 180, 180)
                        .addComponent(chkCaja)
                        .addGap(90, 90, 90))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnAceptar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnDetalles)
                        .addGap(419, 419, 419)
                        .addComponent(btnImprimir, javax.swing.GroupLayout.DEFAULT_SIZE, 189, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(btnCerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                            .addComponent(btnFiltrar, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                            .addComponent(txtFechaHasta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtFechaDesde, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(chkCaja)
                            .addComponent(chkFecha)
                            .addComponent(chkUsuario)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                            .addComponent(txtBusqueda, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 307, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnDetalles)
                    .addComponent(btnAceptar)
                    .addComponent(btnImprimir))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnFiltrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFiltrarActionPerformed
        // TODO add your handling code here:
        buscar();
        
}//GEN-LAST:event_btnFiltrarActionPerformed

    private void btnCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarActionPerformed
        // TODO add your handling code here:
        this.dispose();
}//GEN-LAST:event_btnCerrarActionPerformed

    private void chkFechaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkFechaActionPerformed
        // TODO add your handling code here:
}//GEN-LAST:event_chkFechaActionPerformed

    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed
        // TODO add your handling code here:
        
}//GEN-LAST:event_btnAceptarActionPerformed

    private void btnDetallesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDetallesActionPerformed
        // TODO add your handling code here:
        int sel = this.jTable1.getSelectedRow();
        int id;

        if(sel == -1)
        {
            Dialogos.lanzarAlerta("Ningúna fila ha sido seleccionada.");
        } else {
            //++++++++++++++++++++++++++++
            id = (Integer)this.jTable1.getValueAt(sel, -1);
            //Lanzar ventana y agregarle un listener
            omoikane.principal.Ventas.lanzarDetalles(id);
        }


}//GEN-LAST:event_btnDetallesActionPerformed

    private void btnImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImprimirActionPerformed
        // TODO add your handling code here:
       
}//GEN-LAST:event_btnImprimirActionPerformed

    private void txtBusquedaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBusquedaKeyTyped
        // TODO add your handling code here:
        buscar();
    }//GEN-LAST:event_txtBusquedaKeyTyped

    public boolean getBuscarUsuario()
    {
        return this.chkUsuario.getModel().isSelected();
    }
    public boolean getBuscarCaja() {
        return this.chkCaja.getModel().isSelected();
    }
    public boolean getBuscarFecha() {
        return this.chkFecha.getModel().isSelected();
    }

    public void buscar()
    {
        boolean xUsuario = getBuscarUsuario();
        boolean xCaja = getBuscarCaja();
        boolean xFecha = getBuscarFecha();
        String busqueda = this.txtBusqueda.getText();
        String query    = "SELECT id_venta,id_venta,id_caja,id_almacen,id_cliente,fecha_hora,total FROM ventas WHERE id_almacen="+IDAlmacen+" ";
        if(xUsuario || xCaja || xFecha) { query += "AND ("; }
        if(xUsuario) {
                query += "(id_cliente like '%"+busqueda+"%') ";
        }
        if(xUsuario && (xCaja || xFecha)) { query += "OR "; }
        if(xCaja) {
                query += "(id_caja like '%"+busqueda+"%') ";
        }
        if((xCaja||xUsuario) && xFecha) { query += "OR "; }
        if(xFecha) {

        String fechaDesde     = "";
        String fechaHasta     = "";
        SimpleDateFormat sdf  = new SimpleDateFormat("yyyy-MM-dd");
        if(this.txtFechaDesde.getDate() != null || this.txtFechaHasta.getDate() != null)
        {
            try {
                fechaDesde = sdf.format(this.txtFechaDesde.getDate());
                fechaHasta = sdf.format(this.txtFechaHasta.getDate());
            } catch(Exception e) { omoikane.sistema.Dialogos.lanzarDialogoError(null, "Error en el registro: Fecha inválida", omoikane.sistema.Herramientas.getStackTraceString(e)); }
        }
            
                String whereFecha = "";
        // Determina si se hará una búsqueda por fecha y de ser asi crea la clausua necesaria
        if(fechaHasta != "" || fechaDesde != "")
        {
            if(fechaHasta == "" || fechaDesde == "")
            {
                Dialogos.lanzarAlerta("El intervalo de fechas para filtrar resultados está incompleto, completelo o vacíelo para dejar de leer este mensaje.");
                fechaDesde = ""; fechaHasta = "";
            } else {
                whereFecha = " AND fecha_hora >= '$fechaDesde' AND fecha <= '$fechaHasta'";
            }
        }
        //Comienza la población
        query += whereFecha;
        }
        if(xUsuario || xCaja || xFecha) { query += ")"; }
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
    private javax.swing.JButton btnFiltrar;
    private javax.swing.JButton btnImprimir;
    private javax.swing.JCheckBox chkCaja;
    private javax.swing.JCheckBox chkFecha;
    private javax.swing.JCheckBox chkUsuario;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    public javax.swing.JTextField txtBusqueda;
    private org.jdesktop.swingx.JXDatePicker txtFechaDesde;
    private org.jdesktop.swingx.JXDatePicker txtFechaHasta;
    // End of variables declaration//GEN-END:variables

}