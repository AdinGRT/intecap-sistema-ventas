package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modelo.*;
import vistas.*;

/**
 *
 * @author Adin Rubio
 */
public class ControladorProducto implements ActionListener {

    ProductoDAO productoDAO = new ProductoDAO();
    Producto modeloProducto = new Producto();
    //PrincipalFormulario principalFormulario = new PrincipalFormulario();
    MantenimientoProducto mantenimientoProducto = new MantenimientoProducto();

    public ControladorProducto(MantenimientoProducto mantenimientoProducto) {
        this.mantenimientoProducto = mantenimientoProducto;
        this.mantenimientoProducto.btnAgregar.addActionListener(this);
        this.mantenimientoProducto.btnConsultar.addActionListener(this);
        this.mantenimientoProducto.btnLimpiar.addActionListener(this);
        this.mantenimientoProducto.btnModificar.addActionListener(this);
        this.mantenimientoProducto.btnEliminar.addActionListener(this);
        llenarTabla(this.mantenimientoProducto.tablaProducto);
    }

    public void insertarProducto() {
        if (mantenimientoProducto.txtId.getText().equals("") || mantenimientoProducto.txtNombre.getText().equals("")
                || mantenimientoProducto.txtPrecio.getText().equals("") || mantenimientoProducto.txtStock.getText().equals("")
                || mantenimientoProducto.txtEstado.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Uno o mas campos están vacíos. Llenar!");
        } else {
            try {
                modeloProducto.setIdProducto(Integer.parseInt(mantenimientoProducto.txtId.getText()));
                modeloProducto.setNombres(mantenimientoProducto.txtNombre.getText());
                modeloProducto.setPrecio(Double.parseDouble(mantenimientoProducto.txtPrecio.getText()));
                modeloProducto.setStock(Integer.parseInt(mantenimientoProducto.txtStock.getText()));
                modeloProducto.setEstado(mantenimientoProducto.txtEstado.getText());
                String respuesta = productoDAO.createProducto(modeloProducto);
                if (respuesta != null) {
                    JOptionPane.showMessageDialog(null, respuesta);
                    limpiarControles();
                    llenarTabla(this.mantenimientoProducto.tablaProducto);
                }
            } catch (NumberFormatException e) {
                System.out.println(e);
                JOptionPane.showMessageDialog(null, "Uno o mas campos tienen un formato inválido.");
                limpiarControles();
            }
        }
    }

    public void mostrarProducto() {
        if (mantenimientoProducto.txtId.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Ingrese un Id!");
        } else {
            try {
                int valor = Integer.parseInt(mantenimientoProducto.txtId.getText());
                modeloProducto = productoDAO.readProducto(valor);
                mantenimientoProducto.txtNombre.setText(modeloProducto.getNombres());
                mantenimientoProducto.txtPrecio.setText(Double.toString(modeloProducto.getPrecio()));
                mantenimientoProducto.txtStock.setText(Integer.toString(modeloProducto.getStock()));
                mantenimientoProducto.txtEstado.setText(modeloProducto.getEstado());
                if (modeloProducto.getIdProducto() == 0) {
                    JOptionPane.showMessageDialog(null, "Registro inválido!");
                    limpiarControles();
                    llenarTabla(this.mantenimientoProducto.tablaProducto);
                }
            } catch (NumberFormatException e) {
                System.out.println(e);
                JOptionPane.showMessageDialog(null, "Debe ingresar un número en Id.");
                limpiarControles();
            }
        }
    }

    public void modificarProducto() {
        if (mantenimientoProducto.txtId.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Ingrese un Id!");
        } else {
            try {
            modeloProducto.setIdProducto(Integer.parseInt(mantenimientoProducto.txtId.getText()));
            modeloProducto.setNombres(mantenimientoProducto.txtNombre.getText());
            modeloProducto.setPrecio(Double.parseDouble(mantenimientoProducto.txtPrecio.getText()));
            modeloProducto.setStock(Integer.parseInt(mantenimientoProducto.txtStock.getText()));
            modeloProducto.setEstado(mantenimientoProducto.txtEstado.getText());
            String respuestaActualizar = this.productoDAO.updateProducto(modeloProducto);
            if (respuestaActualizar != null) {
                JOptionPane.showMessageDialog(null, respuestaActualizar);
                limpiarControles();
                llenarTabla(this.mantenimientoProducto.tablaProducto);
            }
            } catch (NumberFormatException e) {
                System.out.println(e);
                JOptionPane.showMessageDialog(null, "Uno o mas campos tienen un formato inválido.");
                limpiarControles();
            }
        }
    }

    public void eliminarProducto() {
        if (mantenimientoProducto.txtId.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Ingrese un Id!");
        } else {
            try {
            modeloProducto.setIdProducto(Integer.parseInt(mantenimientoProducto.txtId.getText()));
            String respuestaEliminar = this.productoDAO.deleteProducto(modeloProducto.getIdProducto());
            if (respuestaEliminar != null) {
                JOptionPane.showMessageDialog(null, respuestaEliminar);
                limpiarControles();
                llenarTabla(this.mantenimientoProducto.tablaProducto);
            }
            } catch (NumberFormatException e) {
                System.out.println(e);
                JOptionPane.showMessageDialog(null, "Debe ingresar un número en Id");
                limpiarControles();
            }
        }
    }

    public void llenarTabla(JTable tablaDatos) {
        DefaultTableModel modeloTabla = new DefaultTableModel();
        tablaDatos.setModel(modeloTabla);
        modeloTabla.addColumn("Id");
        modeloTabla.addColumn("Nombre");
        modeloTabla.addColumn("Precio");
        modeloTabla.addColumn("Stock");
        modeloTabla.addColumn("Estado");
        Object[] columna = new Object[5];
        int numRegistro = productoDAO.listaProducto().size();
        for (int i = 0; i < numRegistro; i++) {
            columna[0] = productoDAO.listaProducto().get(i).getIdProducto();
            columna[1] = productoDAO.listaProducto().get(i).getNombres();
            columna[2] = productoDAO.listaProducto().get(i).getPrecio();
            columna[3] = productoDAO.listaProducto().get(i).getStock();
            columna[4] = productoDAO.listaProducto().get(i).getEstado();
            modeloTabla.addRow(columna);
        }
    }

    public void limpiarControles() {
        mantenimientoProducto.txtId.setText(null);
        mantenimientoProducto.txtNombre.setText(null);
        mantenimientoProducto.txtPrecio.setText(null);
        mantenimientoProducto.txtStock.setText(null);
        mantenimientoProducto.txtEstado.setText(null);
        mantenimientoProducto.txtId.requestFocus();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == mantenimientoProducto.btnAgregar) {
            insertarProducto();
        }
        if (e.getSource() == mantenimientoProducto.btnConsultar) {
            mostrarProducto();
        }
        if (e.getSource() == mantenimientoProducto.btnLimpiar) {
            limpiarControles();
        }
        if (e.getSource() == mantenimientoProducto.btnModificar) {
            modificarProducto();
        }
        if (e.getSource() == mantenimientoProducto.btnEliminar) {
            eliminarProducto();
        }
    }
}
