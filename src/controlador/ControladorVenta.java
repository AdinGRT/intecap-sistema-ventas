package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.*;
import vistas.GenerarVenta;

/**
 *
 * @author Adin Rubio
 */
public class ControladorVenta implements ActionListener {
    
    Cliente modeloCliente = new Cliente();
    ClienteDAO clienteDAO = new ClienteDAO();
    Producto modeloProducto = new Producto();
    ProductoDAO productoDAO = new ProductoDAO();
    Ventas modeloVentas = new Ventas();
    VentasDAO ventasDAO = new VentasDAO();
    DetalleVentas modeloDetalleVentas = new DetalleVentas();
    GenerarVenta generarVenta = new GenerarVenta();
    DefaultTableModel detalleModel = new DefaultTableModel();
    int idCliente;
    int item = 1;
    int idProducto;
    String nombreProducto;
    double precioProducto;
    int cantidad;
    int stock;
    double totalPagar;
    int idVendedor;
    int validarVenta = 2;

    public ControladorVenta(GenerarVenta generarVenta, int id, String user) {
        this.generarVenta = generarVenta;
        this.generarVenta.btnBuscarCliente.addActionListener(this);
        this.generarVenta.btnBuscarProducto.addActionListener(this);
        this.generarVenta.btnAgregarProducto.addActionListener(this);
        this.generarVenta.btnRealizarVenta.addActionListener(this);
        //this.generarVenta.txtVendedor.setText(Integer.toString(id));
        //Yo meti en el cuadro de texto el usuario pero tambien podria ser el idVendedor
        this.generarVenta.txtVendedor.setText(user);
        this.idVendedor = id;
        fecha();
        generarSerie();
    }

    public void buscarCliente() {
        if (generarVenta.txtCodigoCliente.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Ingrese un Id de Cliente!");
        } else {
            modeloCliente = clienteDAO.searchCliente(generarVenta.txtCodigoCliente.getText());
            generarVenta.txtNit.setText(modeloCliente.getCui());
            generarVenta.txtNombreCliente.setText(modeloCliente.getNombres());
            generarVenta.txtDireccion.setText(modeloCliente.getDireccion());
            idCliente = modeloCliente.getIdCliente();
            if (modeloCliente.getCui() == null) {
                JOptionPane.showMessageDialog(null, "Registro inválido!");
                limpiarCliente();
            }
        }
    }

    public void buscarProducto() {
        if (generarVenta.txtIdProducto.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Ingrese un Id de Producto.");
        } else {
            try {
                modeloProducto = productoDAO.readProducto(Integer.parseInt(generarVenta.txtIdProducto.getText()));
                generarVenta.txtPrecio.setText(Double.toString(modeloProducto.getPrecio()));
                generarVenta.txtStock.setText(Integer.toString(modeloProducto.getStock()));
                generarVenta.txtNombreProducto.setText(modeloProducto.getNombres());
                if (modeloProducto.getIdProducto() == 0) {
                    JOptionPane.showMessageDialog(null, "Producto no encontrado.");
                    limpiarProducto();
                }
            } catch (NumberFormatException e) {
                System.out.println(e);
                JOptionPane.showMessageDialog(null, "Debes ingresar un número.");
                limpiarProducto();
            }
        }
    }

    public void agregarProducto() {
        if (generarVenta.txtIdProducto.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Ingrese un Id de Producto.");
        } else if (generarVenta.txtNombreProducto.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Presione el botón buscar.");
        } else if (generarVenta.txtCantidad.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Ingrese cantidad de Productos.");
        } else {
            detalleModel = (DefaultTableModel) generarVenta.tablaDetalle.getModel();
            idProducto = modeloProducto.getIdProducto();
            nombreProducto = generarVenta.txtNombreProducto.getText();
            precioProducto = Double.parseDouble(generarVenta.txtPrecio.getText());
            cantidad = Integer.parseInt(generarVenta.txtCantidad.getText());
            stock = Integer.parseInt(generarVenta.txtStock.getText());
            totalPagar = cantidad * precioProducto;
            ArrayList lista = new ArrayList();
            if (stock > 0) {
                lista.add(item);
                lista.add(idProducto);
                lista.add(nombreProducto);
                lista.add(cantidad);
                lista.add(precioProducto);
                lista.add(totalPagar);
                Object[] objeto = new Object[6];
                objeto[0] = lista.get(0);
                objeto[1] = lista.get(1);
                objeto[2] = lista.get(2);
                objeto[3] = lista.get(3);
                objeto[4] = lista.get(4);
                objeto[5] = lista.get(5);
                detalleModel.addRow(objeto);
                generarVenta.tablaDetalle.setModel(detalleModel);
                calcularTotal();
                item++;
            }
            limpiarProducto();
        }
    }

    public void guardarVenta() {
        modeloVentas.setIdVentas(Integer.parseInt(generarVenta.txtNumFactura.getText()));
        //modeloVentas.setNumeroVentas();
        modeloVentas.setFechaVentas(generarVenta.txtFecha.getText());
        modeloVentas.setMonto(Double.parseDouble(generarVenta.txtTotalPagar.getText()));
        modeloVentas.setEstado("1");
        modeloVentas.setCliente_idCliente(idCliente);
        modeloVentas.setVendedor_idVendedor(idVendedor);
        int respuesta = ventasDAO.guardarVentas(modeloVentas);
        this.validarVenta = respuesta;
    }

    public void guardarDetalleVenta() {
        for (int i = 0; i < generarVenta.tablaDetalle.getRowCount(); i++) {
            modeloDetalleVentas.setVentas_idVentas(Integer.parseInt(generarVenta.txtNumFactura.getText()));
            modeloDetalleVentas.setIdDetalleVentas(Integer.parseInt(generarVenta.tablaDetalle.getValueAt(i, 0).toString()));
            modeloDetalleVentas.setProducto_idProducto(Integer.parseInt(generarVenta.tablaDetalle.getValueAt(i, 1).toString()));
            modeloDetalleVentas.setCantidad(Integer.parseInt(generarVenta.tablaDetalle.getValueAt(i, 3).toString()));
            modeloDetalleVentas.setPrecioVenta(Double.parseDouble(generarVenta.tablaDetalle.getValueAt(i, 4).toString()));
            int respuesta = ventasDAO.guardarDetalleVentas(modeloDetalleVentas);
            this.validarVenta = respuesta;
        }
    }

    public void generarSerie() {
        String serie = ventasDAO.NoSerieVentas();
        if (serie == null) {
            generarVenta.txtNumFactura.setText("1");
        } else {
            int noGenerado = (Integer.parseInt(serie)) + 1;
            generarVenta.txtNumFactura.setText(Integer.toString(noGenerado));
        }
    }

    public void actualizarStock() {
        for (int i = 0; i < generarVenta.tablaDetalle.getRowCount(); i++) {
            idProducto = Integer.parseInt(generarVenta.tablaDetalle.getValueAt(i, 1).toString());
            cantidad = Integer.parseInt(generarVenta.tablaDetalle.getValueAt(i, 3).toString());
            modeloProducto = productoDAO.readProducto(idProducto);
            int saldo = modeloProducto.getStock() - cantidad;
            productoDAO.updateStock(saldo, idProducto);
        }
    }

    public void fecha() {
        Calendar calendar = new GregorianCalendar();
        generarVenta.txtFecha.setText(calendar.get(Calendar.YEAR) + "-" + calendar.get(Calendar.MONTH) + "-" + calendar.get(Calendar.DAY_OF_MONTH));
    }

    public void calcularTotal() {
        totalPagar = 0;
        for (int i = 0; i < generarVenta.tablaDetalle.getRowCount(); i++) {
            cantidad = Integer.parseInt(generarVenta.tablaDetalle.getValueAt(i, 3).toString());
            precioProducto = Double.parseDouble(generarVenta.tablaDetalle.getValueAt(i, 4).toString());
            totalPagar += precioProducto * cantidad;
        }
        generarVenta.txtTotalPagar.setText(Double.toString(totalPagar));
    }

    public void limpiarControles() {
        limpiarCliente();
        limpiarProducto();
        limpiarTabla();
        generarVenta.txtTotalPagar.setText("");
    }
    
    public void limpiarProducto() {
        generarVenta.txtIdProducto.setText("");
        generarVenta.txtNombreProducto.setText("");
        generarVenta.txtPrecio.setText("");
        generarVenta.txtStock.setText("");
        generarVenta.txtCantidad.setText("");
    }
    
    public void limpiarCliente() {
        generarVenta.txtCodigoCliente.setText("");
        generarVenta.txtNombreCliente.setText("");
        generarVenta.txtDireccion.setText("");
        generarVenta.txtNit.setText("");
    }
    
    public void limpiarTabla() {
        detalleModel.setRowCount(0);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == generarVenta.btnBuscarCliente) {
            buscarCliente();
        }
        if (e.getSource() == generarVenta.btnBuscarProducto) {
            buscarProducto();
        }
        if (e.getSource() == generarVenta.btnAgregarProducto) {
            agregarProducto();
        }
        if (e.getSource() == generarVenta.btnRealizarVenta) {
            if (generarVenta.txtTotalPagar.getText().equals("") || generarVenta.txtCodigoCliente.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Completa los datos del detalle y cliente.");
            } else {
                guardarVenta();
                guardarDetalleVenta();
            }
            if (validarVenta == 1) {
                actualizarStock();
                JOptionPane.showMessageDialog(null, "La venta se realizó exitosamente.");
                limpiarControles();
                generarSerie();
            } else if (validarVenta == 0) {
                JOptionPane.showMessageDialog(null, "No se pudo realizar la venta.");
            }
        }
    }
}
