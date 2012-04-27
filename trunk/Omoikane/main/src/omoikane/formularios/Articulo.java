package omoikane.formularios;

import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import omoikane.sistema.*;

/*
 * @author Octavio
 */
public class Articulo extends javax.swing.JInternalFrame {

    BufferedImage fondo;
    public int ID;

    /** Creates new form Articulo */
    public Articulo() {
        initComponents();
        //Instrucciones para el funcionamiento del fondo semistransparente
        this.setOpaque(false);
        ((JPanel)this.getContentPane()).setOpaque(false);
        this.getLayeredPane().setOpaque(false);
        this.getRootPane().setOpaque(false);
        this.generarFondo(this);

        Herramientas.centrarVentana(this);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        btnCerrar = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtIDArticulo = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtCodigo = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtIDGrupoDes = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtDescripcion = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtUnidad = new javax.swing.JComboBox();
        jLabel10 = new javax.swing.JLabel();
        txtExistencias = new javax.swing.JTextField();
        txtPrecio = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtUModificacion = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        txtDescuento = new javax.swing.JTextField();
        txtIDGrupo = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtIDLinea = new javax.swing.JTextField();
        txtIDLineaDes = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblCodigos = new javax.swing.JTable();
        btnAddCode = new javax.swing.JButton();
        btnDelCode = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        txtImpuestos = new javax.swing.JTextField();
        txtUtilidad = new javax.swing.JTextField();
        txtDescuento2 = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        txtCosto = new javax.swing.JFormattedTextField();
        txtPrecioTotal = new javax.swing.JFormattedTextField();
        txtDesctoPorcentaje = new javax.swing.JFormattedTextField();
        txtImpuestosPorc = new javax.swing.JFormattedTextField();
        txtUtilidadPorc = new javax.swing.JFormattedTextField();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        txtComentarios = new javax.swing.JTextArea();

        setTitle("Artículos");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Arial", 1, 36));
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Artículo");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        btnCerrar.setText("Cerrar");
        btnCerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarActionPerformed(evt);
            }
        });
        getContentPane().add(btnCerrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 470, 80, -1));

        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        getContentPane().add(btnGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 470, 80, -1));

        btnModificar.setText("Modificar");
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });
        getContentPane().add(btnModificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 470, 100, -1));
        getContentPane().add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 490, 10, 10));

        jPanel1.setOpaque(false);
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Arial", 1, 12));
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("ID Artículo:");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, -1, 20));

        txtIDArticulo.setBackground(new java.awt.Color(55, 55, 255));
        txtIDArticulo.setEditable(false);
        txtIDArticulo.setForeground(new java.awt.Color(255, 255, 255));
        txtIDArticulo.setBorder(null);
        txtIDArticulo.setFocusable(false);
        jPanel1.add(txtIDArticulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 20, 240, 20));

        jLabel3.setFont(new java.awt.Font("Arial", 1, 12));
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("<html>Código <br>principal:<html>");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 90, 30));

        txtCodigo.setEditable(false);
        jPanel1.add(txtCodigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 50, 240, -1));

        jLabel8.setFont(new java.awt.Font("Arial", 1, 12));
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("ID Línea [F1]:");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 90, 20));

        txtIDGrupoDes.setBackground(new java.awt.Color(55, 55, 255));
        txtIDGrupoDes.setEditable(false);
        txtIDGrupoDes.setForeground(new java.awt.Color(255, 255, 255));
        txtIDGrupoDes.setBorder(null);
        txtIDGrupoDes.setFocusable(false);
        jPanel1.add(txtIDGrupoDes, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 110, 190, 20));

        jLabel4.setFont(new java.awt.Font("Arial", 1, 12));
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Descripción:");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, -1, 20));

        txtDescripcion.setEditable(false);
        jPanel1.add(txtDescripcion, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 140, 240, -1));

        jLabel5.setFont(new java.awt.Font("Arial", 1, 12));
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Unidad:");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 170, -1, 20));

        txtUnidad.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "PZA", "CAJA", "PAQ", "KG", "LT" }));
        txtUnidad.setEnabled(false);
        jPanel1.add(txtUnidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 170, 240, -1));

        jLabel10.setFont(new java.awt.Font("Arial", 1, 12));
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Existencias:");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 200, -1, 20));

        txtExistencias.setBackground(new java.awt.Color(55, 55, 255));
        txtExistencias.setEditable(false);
        txtExistencias.setForeground(new java.awt.Color(255, 255, 255));
        txtExistencias.setFocusable(false);
        jPanel1.add(txtExistencias, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 200, 240, 30));

        txtPrecio.setBackground(new java.awt.Color(55, 55, 255));
        txtPrecio.setEditable(false);
        txtPrecio.setFont(new java.awt.Font("Tahoma", 1, 18));
        txtPrecio.setForeground(new java.awt.Color(255, 255, 255));
        txtPrecio.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtPrecio.setBorder(null);
        jPanel1.add(txtPrecio, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 280, 240, 30));

        jLabel12.setFont(new java.awt.Font("Arial", 1, 18));
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Precio:");
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 280, -1, 30));

        jLabel7.setFont(new java.awt.Font("Arial", 1, 12));
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("<html>Última<br>Modificación:</html>");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 320, -1, -1));

        txtUModificacion.setBackground(new java.awt.Color(55, 55, 255));
        txtUModificacion.setEditable(false);
        txtUModificacion.setForeground(new java.awt.Color(255, 255, 255));
        txtUModificacion.setBorder(null);
        txtUModificacion.setFocusable(false);
        jPanel1.add(txtUModificacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 320, 240, 30));
        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, -40, -1, 20));

        jLabel19.setFont(new java.awt.Font("Arial", 1, 12));
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText("Descuento:");
        jPanel1.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 250, -1, 20));

        txtDescuento.setBackground(new java.awt.Color(55, 55, 255));
        txtDescuento.setEditable(false);
        txtDescuento.setForeground(new java.awt.Color(255, 255, 255));
        txtDescuento.setBorder(null);
        jPanel1.add(txtDescuento, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 240, 240, 30));

        txtIDGrupo.setEditable(false);
        jPanel1.add(txtIDGrupo, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 110, 40, -1));

        jLabel9.setFont(new java.awt.Font("Arial", 1, 12));
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("ID Grupo [F1] :");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, 80, 20));

        txtIDLinea.setEditable(false);
        jPanel1.add(txtIDLinea, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 80, 40, -1));

        txtIDLineaDes.setBackground(new java.awt.Color(55, 55, 255));
        txtIDLineaDes.setEditable(false);
        txtIDLineaDes.setForeground(new java.awt.Color(255, 255, 255));
        txtIDLineaDes.setBorder(null);
        txtIDLineaDes.setFocusable(false);
        jPanel1.add(txtIDLineaDes, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 80, 190, 20));

        jTabbedPane1.addTab("General", jPanel1);

        jPanel2.setOpaque(false);
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblCodigos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código"
            }
        ));
        jScrollPane1.setViewportView(tblCodigos);

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 340, 300));

        btnAddCode.setText("+");
        jPanel2.add(btnAddCode, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 320, 40, 20));

        btnDelCode.setText("-");
        jPanel2.add(btnDelCode, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 320, -1, 20));

        jTabbedPane1.addTab("Códigos", jPanel2);

        jPanel5.setOpaque(false);
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Impuestos (%):");
        jPanel5.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 170, -1, 20));

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 24));
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("Costo:");
        jPanel5.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 50, -1, 30));

        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("Utilidad (%):");
        jPanel5.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, -1, 20));

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 18));
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("Detalles del precio y costo");
        jPanel5.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, -1, -1));

        jLabel20.setFont(new java.awt.Font("Tahoma", 0, 24));
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setText("Precio:");
        jPanel5.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 290, -1, 30));

        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("$:");
        jPanel5.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 170, -1, 20));

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 24));
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("$");
        jPanel5.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 290, 20, 30));

        jLabel21.setFont(new java.awt.Font("Tahoma", 0, 24));
        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setText("$");
        jPanel5.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 50, 20, 30));

        jLabel22.setForeground(new java.awt.Color(255, 255, 255));
        jLabel22.setText("$:");
        jPanel5.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 120, -1, 20));

        jLabel24.setForeground(new java.awt.Color(255, 255, 255));
        jLabel24.setText("$:");
        jPanel5.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 220, -1, 20));

        txtImpuestos.setBackground(new java.awt.Color(55, 55, 255));
        txtImpuestos.setEditable(false);
        txtImpuestos.setFont(new java.awt.Font("Arial", 0, 14));
        txtImpuestos.setForeground(new java.awt.Color(255, 255, 255));
        txtImpuestos.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtImpuestos.setBorder(null);
        txtImpuestos.setFocusable(false);
        jPanel5.add(txtImpuestos, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 170, 110, 30));

        txtUtilidad.setBackground(new java.awt.Color(55, 55, 255));
        txtUtilidad.setEditable(false);
        txtUtilidad.setFont(new java.awt.Font("Arial", 0, 14));
        txtUtilidad.setForeground(new java.awt.Color(255, 255, 255));
        txtUtilidad.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtUtilidad.setBorder(null);
        txtUtilidad.setFocusable(false);
        jPanel5.add(txtUtilidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 120, 110, 30));

        txtDescuento2.setBackground(new java.awt.Color(55, 55, 255));
        txtDescuento2.setEditable(false);
        txtDescuento2.setFont(new java.awt.Font("Arial", 0, 14));
        txtDescuento2.setForeground(new java.awt.Color(255, 255, 255));
        txtDescuento2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtDescuento2.setBorder(null);
        txtDescuento2.setFocusable(false);
        jPanel5.add(txtDescuento2, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 220, 110, 30));

        jLabel25.setForeground(new java.awt.Color(255, 255, 255));
        jLabel25.setText("el precio siguiente no incluye los demas descuentos");
        jPanel5.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 270, 340, 20));

        jLabel26.setForeground(new java.awt.Color(255, 255, 255));
        jLabel26.setText("Descuento (%):");
        jPanel5.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 220, -1, 20));

        jLabel27.setForeground(new java.awt.Color(255, 255, 255));
        jLabel27.setText("Nota :El descuento que aparece es solo por articulo  ");
        jPanel5.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 250, 340, 20));

        txtCosto.setEditable(false);
        txtCosto.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        txtCosto.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCosto.setToolTipText("Solo numeros");
        txtCosto.setFont(new java.awt.Font("Arial", 0, 14));
        txtCosto.setHighlighter(null);
        txtCosto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCostoKeyReleased(evt);
            }
        });
        jPanel5.add(txtCosto, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 50, 140, 30));

        txtPrecioTotal.setEditable(false);
        txtPrecioTotal.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        txtPrecioTotal.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtPrecioTotal.setToolTipText("Solo numeros");
        txtPrecioTotal.setFont(new java.awt.Font("Arial", 0, 14));
        txtPrecioTotal.setHighlighter(null);
        txtPrecioTotal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPrecioTotalKeyReleased(evt);
            }
        });
        jPanel5.add(txtPrecioTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 290, 140, 30));

        txtDesctoPorcentaje.setEditable(false);
        txtDesctoPorcentaje.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        txtDesctoPorcentaje.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtDesctoPorcentaje.setToolTipText("Solo numeros");
        txtDesctoPorcentaje.setFont(new java.awt.Font("Arial", 0, 14));
        txtDesctoPorcentaje.setHighlighter(null);
        txtDesctoPorcentaje.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDesctoPorcentajeKeyReleased(evt);
            }
        });
        jPanel5.add(txtDesctoPorcentaje, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 220, 110, 30));

        txtImpuestosPorc.setEditable(false);
        txtImpuestosPorc.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        txtImpuestosPorc.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtImpuestosPorc.setToolTipText("Solo numeros");
        txtImpuestosPorc.setFont(new java.awt.Font("Arial", 0, 14));
        txtImpuestosPorc.setHighlighter(null);
        txtImpuestosPorc.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtImpuestosPorcKeyReleased(evt);
            }
        });
        jPanel5.add(txtImpuestosPorc, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 170, 110, 30));

        txtUtilidadPorc.setEditable(false);
        txtUtilidadPorc.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        txtUtilidadPorc.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtUtilidadPorc.setToolTipText("Solo numeros");
        txtUtilidadPorc.setFont(new java.awt.Font("Arial", 0, 14));
        txtUtilidadPorc.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtUtilidadPorcKeyReleased(evt);
            }
        });
        jPanel5.add(txtUtilidadPorc, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 120, 110, 30));

        jTabbedPane1.addTab("Precio", jPanel5);

        txtComentarios.setColumns(25);
        txtComentarios.setRows(20);
        jScrollPane4.setViewportView(txtComentarios);

        jPanel7.add(jScrollPane4);

        jTabbedPane1.addTab("Notas", jPanel7);

        getContentPane().add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 370, 400));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarActionPerformed
        // TODO add your handling code here:
        this.dispose();
}//GEN-LAST:event_btnCerrarActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        // TODO add your handling code here:
        omoikane.principal.Articulos.guardar(this);
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        // TODO add your handling code here:
        omoikane.principal.Articulos.modificar(this);
    }//GEN-LAST:event_btnModificarActionPerformed

    private void txtPrecioTotalKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPrecioTotalKeyReleased
        // TODO add your handling code here:
        omoikane.principal.Articulos.recalcularUtilidad(this);
}//GEN-LAST:event_txtPrecioTotalKeyReleased

    private void txtDesctoPorcentajeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDesctoPorcentajeKeyReleased
        // TODO add your handling code here:
        omoikane.principal.Articulos.recalcularCampos(this);
    }//GEN-LAST:event_txtDesctoPorcentajeKeyReleased

    private void txtImpuestosPorcKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtImpuestosPorcKeyReleased
        // TODO add your handling code here:
        omoikane.principal.Articulos.recalcularCampos(this);
    }//GEN-LAST:event_txtImpuestosPorcKeyReleased

    private void txtUtilidadPorcKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUtilidadPorcKeyReleased
        // TODO add your handling code here:
        omoikane.principal.Articulos.recalcularCampos(this);
    }//GEN-LAST:event_txtUtilidadPorcKeyReleased

    private void txtCostoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCostoKeyReleased
        // TODO add your handling code here:
        omoikane.principal.Articulos.recalcularCampos(this);
    }//GEN-LAST:event_txtCostoKeyReleased

    public void setEditable(boolean editable)
    {
        this.tblCodigos.setEnabled(editable);
        this.txtCodigo.setEditable(editable);
        this.txtComentarios.setEditable(editable);
        this.txtCosto.setEditable(editable);
        this.txtDescripcion.setEditable(editable);
        this.txtDesctoPorcentaje.setEditable(editable);
        this.txtDescuento2.setEditable(editable);
        this.txtIDGrupo.setEditable(editable);
        this.txtIDLinea.setEditable(editable);
        this.txtImpuestos.setEditable(editable);
        this.txtImpuestosPorc.setEditable(editable);
        this.txtPrecioTotal.setEditable(editable);
        this.txtUnidad.setEnabled(editable);
        this.txtUtilidad.setEditable(editable);
        this.txtUtilidadPorc.setEditable(editable);

    }
    public void setModoDetalles()
    {
        setEditable(false);
        this.txtExistencias.setBorder(null);
        this.btnGuardar.setVisible(false);
        this.btnModificar.setVisible(false);

    }
    public void setModoModificar()
    {
        setEditable(true);
        this.txtExistencias.setBorder(null);
        this.btnGuardar.setVisible(false);
        this.btnModificar.setVisible(true);
    }
    public void setModoNuevo()
    {
        setEditable(true);
        this.txtExistencias.setEditable(true);
        this.txtExistencias.setFocusable(true);
        this.txtExistencias.setBackground(new Color(240,240,240));
        this.txtExistencias.setForeground(Color.BLACK);
        this.btnModificar.setVisible(false);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddCode;
    private javax.swing.JButton btnCerrar;
    private javax.swing.JButton btnDelCode;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable tblCodigos;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextArea txtComentarios;
    private javax.swing.JFormattedTextField txtCosto;
    private javax.swing.JTextField txtDescripcion;
    private javax.swing.JFormattedTextField txtDesctoPorcentaje;
    private javax.swing.JTextField txtDescuento;
    private javax.swing.JTextField txtDescuento2;
    private javax.swing.JTextField txtExistencias;
    private javax.swing.JTextField txtIDArticulo;
    public javax.swing.JTextField txtIDGrupo;
    public javax.swing.JTextField txtIDGrupoDes;
    public javax.swing.JTextField txtIDLinea;
    public javax.swing.JTextField txtIDLineaDes;
    private javax.swing.JTextField txtImpuestos;
    private javax.swing.JFormattedTextField txtImpuestosPorc;
    private javax.swing.JTextField txtPrecio;
    private javax.swing.JFormattedTextField txtPrecioTotal;
    private javax.swing.JTextField txtUModificacion;
    private javax.swing.JComboBox txtUnidad;
    private javax.swing.JTextField txtUtilidad;
    private javax.swing.JFormattedTextField txtUtilidadPorc;
    // End of variables declaration//GEN-END:variables

    public String       getTxtCodigo()              {return txtCodigo.getText();}
    public String       getTxtDescripcion()         {return txtDescripcion.getText();}
    public String       getTxtIDArticulo()          {return txtIDArticulo.getText();}
    public String       getTxtUnidad()              {return String.valueOf(txtUnidad.getSelectedItem());}
    public String       getTxtUModificacion()       {return txtUModificacion.getText();}
    public String       getTxtIDLinea()             {return txtIDLinea.getText();}
    public String       getTxtIDGrupo()             {return txtIDGrupo.getText();}
    public String       getTxtDescuento()           {return txtDescuento.getText(); }
    public String       getTxtCosto()               {return txtCosto.getText(); }
    public String       getTxtExistencias()         {return txtExistencias.getText(); }
    public String       getTxtPrecio()              {return txtPrecio.getText();}
    public String       getTxtComentarios()         {return txtComentarios.getText();}
    public Component    getIDLinea()                {return txtIDLinea; }
    public Component    getIDGrupo()                {return txtIDGrupo; }
    public JTextField   getTxtImpuestos()           {return txtImpuestos;}
    public JTextField   getCampoID()                {return txtIDLinea;}
    public JTextField   getCampoGrupo()             {return txtIDGrupo;}
    public JTextField   getTxtDesctoPorcentaje()    {return txtDesctoPorcentaje;}
    public JTextField   getTxtDescuento2()          {return txtDescuento2;}
    public JTextField   getTxtImpuestos3()          {return txtImpuestos;}
    public JTextField   getTxtImpuestosPorc()       {return txtImpuestosPorc;}
    public JTextField   getTxtPrecioTotal()         {return txtPrecioTotal;}
    public JTextField   getTxtUtilidadPorc()        {return txtUtilidadPorc;}
    public JTextField   getTxtUtilidad()            {return txtUtilidad;}
    public void setTxtCodigo(String txtCodigo) {this.txtCodigo.setText(txtCodigo);}
    public void setTxtDescripcion(String txtDescripcion) {this.txtDescripcion.setText(txtDescripcion);}
    public void setTxtIDArticulo(String txtIDArticulo) {this.txtIDArticulo.setText(txtIDArticulo);}
    public void setTxtUnidad(String txt){this.txtUnidad.setSelectedItem(txt);}
    public void setTxtUModificacion(String txt){txtUModificacion.setText(txt);}
    public void setTxtIDLinea(String IDLinea){txtIDLinea.setText(IDLinea);}
    public void setTxtIDGrupo(String IDGrupo){txtIDGrupo.setText(IDGrupo);}
    public void setTxtIDLineaDes(String IDLinea){txtIDLineaDes.setText(IDLinea);}
    public void setTxtIDGrupoDes(String IDGrupo){txtIDGrupoDes.setText(IDGrupo);}
    public void setTxtDescuento(String descuento)     { txtDescuento.setText(descuento); }
    public void setTxtCosto(String costo)             { txtCosto.setText(costo); }
    public void setTxtExistencias(String existencias) { txtExistencias.setText(existencias); }
    public void setTxtPrecio(String precio) {this.txtPrecio.setText(precio);}
    public void setTxtPrecioTotal(String precio) {this.txtPrecioTotal.setText(precio);}
    public void setTxtImpuestos(String txt){this.getTxtPrecioTotal().setText(txt);}
    public void setTxtDesctoPorcentaje(String txtDestoPorcentaje) {this.txtDesctoPorcentaje.setText(txtDestoPorcentaje);}
    public void setTxtDescuento2(javax.swing.JTextField txtDescuento2) {this.txtDescuento2 = txtDescuento2;}
    public void setTxtDescuento3(String descuento) {this.txtDescuento2.setText(descuento);}
    public void setTxtImpuestos3(javax.swing.JTextField txtImpuestos3) {this.txtImpuestos = txtImpuestos3;}
    public void setTxtImpuestos4(String txtImpuestos3) {this.txtImpuestos.setText(txtImpuestos3);}
    public void setTxtImpuestosPorc(String txtImpuestosPorc) {this.txtImpuestosPorc.setText(txtImpuestosPorc);}
    public void setTxtUtilidadPorc(String txtUtilidadPorc) {this.txtUtilidadPorc.setText(txtUtilidadPorc);}
    public void setTxtUtilidadPorcText(String txt) {txtUtilidadPorc.setText(txt);}
    public void setTxtUtilidad(javax.swing.JTextField txtUtilidad) {this.txtUtilidad = txtUtilidad;}
    public void setTxtUtilidad2(String utilidad) {this.txtUtilidad.setText(utilidad);}
    public void setTxtComentarios(String text) {txtComentarios.setText(text);}

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
      g2d.setColor(new Color(55,55,255,225));
      g2d.fillRect(0,0,areaDibujo.width,areaDibujo.height);
      fondo = tmp;
    }
}

/*
 costo.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                costoCaretUpdate(evt);
            }
        });
*/