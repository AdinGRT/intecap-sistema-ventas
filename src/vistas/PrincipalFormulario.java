/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas;

import controlador.*;
import java.awt.Dimension;
import javax.swing.JInternalFrame;

/**
 *
 * @author gian_
 */
public class PrincipalFormulario extends javax.swing.JFrame {

    /**
     * Creates new form FormularioPrincipal
     */
    private String usuario;
    private int idUsuario;
    
    public PrincipalFormulario() {
        initComponents();
        this.setExtendedState(MAXIMIZED_BOTH);
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
    
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        desktopPane = new javax.swing.JDesktopPane();
        labelUsuario = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        menuBar = new javax.swing.JMenuBar();
        mnuMenu = new javax.swing.JMenu();
        mnuAyuda = new javax.swing.JMenuItem();
        mnuSalir = new javax.swing.JMenuItem();
        mnuVentas = new javax.swing.JMenu();
        mnuGenerarVenta = new javax.swing.JMenuItem();
        mnuMantenimiento = new javax.swing.JMenu();
        mnuCliente = new javax.swing.JMenuItem();
        mnuProducto = new javax.swing.JMenuItem();
        mnuVendedor = new javax.swing.JMenuItem();
        mnuReportes = new javax.swing.JMenu();
        mnuReporteVenta = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        desktopPane.setBackground(new java.awt.Color(102, 102, 102));

        labelUsuario.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        labelUsuario.setForeground(new java.awt.Color(255, 255, 255));
        desktopPane.add(labelUsuario);
        labelUsuario.setBounds(200, 10, 70, 30);

        jLabel1.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Usuario autenticado:");
        desktopPane.add(jLabel1);
        jLabel1.setBounds(10, 10, 190, 30);

        mnuMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/home.png"))); // NOI18N
        mnuMenu.setMnemonic('f');
        mnuMenu.setText("Menu");

        mnuAyuda.setMnemonic('o');
        mnuAyuda.setText("Ayuda");
        mnuAyuda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuAyudaActionPerformed(evt);
            }
        });
        mnuMenu.add(mnuAyuda);

        mnuSalir.setMnemonic('x');
        mnuSalir.setText("Salir");
        mnuSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuSalirActionPerformed(evt);
            }
        });
        mnuMenu.add(mnuSalir);

        menuBar.add(mnuMenu);

        mnuVentas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/logoventas.png"))); // NOI18N
        mnuVentas.setMnemonic('e');
        mnuVentas.setText("Ventas");

        mnuGenerarVenta.setMnemonic('t');
        mnuGenerarVenta.setText("Generar Venta");
        mnuGenerarVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuGenerarVentaActionPerformed(evt);
            }
        });
        mnuVentas.add(mnuGenerarVenta);

        menuBar.add(mnuVentas);

        mnuMantenimiento.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/logomantenimiento.png"))); // NOI18N
        mnuMantenimiento.setMnemonic('h');
        mnuMantenimiento.setText("Mantenimiento");

        mnuCliente.setMnemonic('c');
        mnuCliente.setText("Cliente");
        mnuCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuClienteActionPerformed(evt);
            }
        });
        mnuMantenimiento.add(mnuCliente);

        mnuProducto.setMnemonic('a');
        mnuProducto.setText("Producto");
        mnuProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuProductoActionPerformed(evt);
            }
        });
        mnuMantenimiento.add(mnuProducto);

        mnuVendedor.setText("Vendedor");
        mnuVendedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuVendedorActionPerformed(evt);
            }
        });
        mnuMantenimiento.add(mnuVendedor);

        menuBar.add(mnuMantenimiento);

        mnuReportes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/reportes.png"))); // NOI18N
        mnuReportes.setText("Reportes");

        mnuReporteVenta.setText("Reporte de Ventas");
        mnuReporteVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuReporteVentaActionPerformed(evt);
            }
        });
        mnuReportes.add(mnuReporteVenta);

        menuBar.add(mnuReportes);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(desktopPane, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(desktopPane, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void mnuSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuSalirActionPerformed
        System.exit(0);
    }//GEN-LAST:event_mnuSalirActionPerformed

    private void mnuGenerarVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuGenerarVentaActionPerformed
        GenerarVenta generarVenta = new GenerarVenta();
        ControladorVenta controladorVenta = new ControladorVenta(generarVenta, idUsuario, usuario);
        centrarVentana(generarVenta);        
    }//GEN-LAST:event_mnuGenerarVentaActionPerformed

    private void mnuClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuClienteActionPerformed
        MantenimientoCliente mantenimientoCliente = new MantenimientoCliente();
        ControladorCliente controladorCliente = new ControladorCliente(mantenimientoCliente);
        centrarVentana(mantenimientoCliente);
    }//GEN-LAST:event_mnuClienteActionPerformed

    private void mnuProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuProductoActionPerformed
        // TODO add your handling code here:
        MantenimientoProducto mantenimientoProducto = new MantenimientoProducto();
        ControladorProducto controladorProducto = new ControladorProducto(mantenimientoProducto);
        centrarVentana(mantenimientoProducto);
    }//GEN-LAST:event_mnuProductoActionPerformed

    private void mnuVendedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuVendedorActionPerformed
        // TODO add your handling code here:
        MantenimientoVendedor mantenimientoVendedor = new MantenimientoVendedor();
        ControladorVendedor controladorVendedor = new ControladorVendedor(mantenimientoVendedor);
        centrarVentana(mantenimientoVendedor);
    }//GEN-LAST:event_mnuVendedorActionPerformed

    private void mnuReporteVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuReporteVentaActionPerformed
        // TODO add your handling code here:
        ReporteFacturas reporteFacturas = new ReporteFacturas();
        centrarVentana(reporteFacturas);
    }//GEN-LAST:event_mnuReporteVentaActionPerformed

    private void mnuAyudaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuAyudaActionPerformed
        // TODO add your handling code here:
        FormularioAyuda ayuda = new FormularioAyuda();
        centrarVentana(ayuda);
    }//GEN-LAST:event_mnuAyudaActionPerformed
      
    public void centrarVentana(JInternalFrame frame) {
        desktopPane.add(frame);
        Dimension dimPrincipalF = desktopPane.getSize();
        Dimension dimVentasF = frame.getSize();
        frame.setLocation((dimPrincipalF.width - dimVentasF.width)/2,(dimPrincipalF.height - dimVentasF.height)/2);
        frame.show();
    }
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(PrincipalFormulario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PrincipalFormulario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PrincipalFormulario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PrincipalFormulario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PrincipalFormulario().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDesktopPane desktopPane;
    private javax.swing.JLabel jLabel1;
    public javax.swing.JLabel labelUsuario;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenuItem mnuAyuda;
    private javax.swing.JMenuItem mnuCliente;
    private javax.swing.JMenuItem mnuGenerarVenta;
    private javax.swing.JMenu mnuMantenimiento;
    private javax.swing.JMenu mnuMenu;
    private javax.swing.JMenuItem mnuProducto;
    private javax.swing.JMenuItem mnuReporteVenta;
    private javax.swing.JMenu mnuReportes;
    private javax.swing.JMenuItem mnuSalir;
    private javax.swing.JMenuItem mnuVendedor;
    private javax.swing.JMenu mnuVentas;
    // End of variables declaration//GEN-END:variables

}
