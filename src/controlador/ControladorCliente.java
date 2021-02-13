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
public class ControladorCliente implements ActionListener {

    ClienteDAO clienteDAO = new ClienteDAO();
    Cliente modeloCliente = new Cliente();
    MantenimientoCliente mantenimientoCliente = new MantenimientoCliente();

    public ControladorCliente(MantenimientoCliente mantenimientoCliente) {
        this.mantenimientoCliente = mantenimientoCliente;
        this.mantenimientoCliente.btnAgregar.addActionListener(this);
        this.mantenimientoCliente.btnConsultar.addActionListener(this);
        this.mantenimientoCliente.btnLimpiar.addActionListener(this);
        this.mantenimientoCliente.btnModificar.addActionListener(this);
        this.mantenimientoCliente.btnEliminar.addActionListener(this);
        llenarTabla(this.mantenimientoCliente.tablaCliente);
    }

    public void insertarCliente() {
        if (mantenimientoCliente.txtId.getText().equals("") || mantenimientoCliente.txtCui.getText().equals("")
                || mantenimientoCliente.txtNombre.getText().equals("") || mantenimientoCliente.txtDireccion.getText().equals("")
                || mantenimientoCliente.txtEstado.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Uno o mas campos están vacíos. Llenar.");
        } else {
            try {
                modeloCliente.setIdCliente(Integer.parseInt(mantenimientoCliente.txtId.getText()));
                modeloCliente.setCui(mantenimientoCliente.txtCui.getText());
                modeloCliente.setNombres(mantenimientoCliente.txtNombre.getText());
                modeloCliente.setDireccion(mantenimientoCliente.txtDireccion.getText());
                modeloCliente.setEstado(mantenimientoCliente.txtEstado.getText());
                String respuesta = clienteDAO.createCliente(modeloCliente);
                if (respuesta != null) {
                    JOptionPane.showMessageDialog(null, respuesta);
                    limpiarControles();
                    llenarTabla(this.mantenimientoCliente.tablaCliente);
                }
            } catch (NumberFormatException e) {
                System.out.println(e);
                JOptionPane.showMessageDialog(null, "Debe ingresar un número en Id.");
                limpiarControles();
            }
        }
    }

    public void mostrarCliente() {
        if (mantenimientoCliente.txtId.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Ingrese un Id!");
        } else {
            try {
                int valor = Integer.parseInt(mantenimientoCliente.txtId.getText());
                modeloCliente = clienteDAO.readCliente(valor);
                mantenimientoCliente.txtCui.setText(modeloCliente.getCui());
                mantenimientoCliente.txtNombre.setText(modeloCliente.getNombres());
                mantenimientoCliente.txtDireccion.setText(modeloCliente.getDireccion());
                mantenimientoCliente.txtEstado.setText(modeloCliente.getEstado());
                if (modeloCliente.getIdCliente() == 0) {
                    JOptionPane.showMessageDialog(null, "Registro inválido!");
                    limpiarControles();
                    llenarTabla(this.mantenimientoCliente.tablaCliente);
                }
            } catch (NumberFormatException e) {
                System.out.println(e);
                JOptionPane.showMessageDialog(null, "Debe ingresar un número en Id.");
                limpiarControles();
            }
        }
    }

    public void modificarCliente() {
        if (mantenimientoCliente.txtId.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Ingrese un Id!");
        } else {
            try {
                modeloCliente.setIdCliente(Integer.parseInt(mantenimientoCliente.txtId.getText()));
                modeloCliente.setCui(mantenimientoCliente.txtCui.getText());
                modeloCliente.setNombres(mantenimientoCliente.txtNombre.getText());
                modeloCliente.setDireccion(mantenimientoCliente.txtDireccion.getText());
                modeloCliente.setEstado(mantenimientoCliente.txtEstado.getText());
                String respuestaActualizar = this.clienteDAO.updateCliente(modeloCliente);
                if (respuestaActualizar != null) {
                    JOptionPane.showMessageDialog(null, respuestaActualizar);
                    limpiarControles();
                    llenarTabla(this.mantenimientoCliente.tablaCliente);
                }
            } catch (NumberFormatException e) {
                System.out.println(e);
                JOptionPane.showMessageDialog(null, "Debe ingresar un número en Id.");
                limpiarControles();
            }
        }
    }

    public void eliminarCliente() {
        if (mantenimientoCliente.txtId.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Ingrese un Id!");
        } else {
            try {
                modeloCliente.setIdCliente(Integer.parseInt(mantenimientoCliente.txtId.getText()));
                String respuestaEliminar = this.clienteDAO.deleteCliente(modeloCliente.getIdCliente());
                if (respuestaEliminar != null) {
                    JOptionPane.showMessageDialog(null, respuestaEliminar);
                    limpiarControles();
                    llenarTabla(this.mantenimientoCliente.tablaCliente);
                }
            } catch (NumberFormatException e) {
                System.out.println(e);
                JOptionPane.showMessageDialog(null, "Debe ingresar un número en Id.");
                limpiarControles();
            }
        }
    }

    public void llenarTabla(JTable tablaDatos) {
        DefaultTableModel modeloTabla = new DefaultTableModel();
        tablaDatos.setModel(modeloTabla);
        modeloTabla.addColumn("Id");
        modeloTabla.addColumn("Cui");
        modeloTabla.addColumn("Nombre");
        modeloTabla.addColumn("Direccion");
        modeloTabla.addColumn("Estado");
        Object[] columna = new Object[5];
        int numRegistro = clienteDAO.listaCliente().size();
        for (int i = 0; i < numRegistro; i++) {
            columna[0] = clienteDAO.listaCliente().get(i).getIdCliente();
            columna[1] = clienteDAO.listaCliente().get(i).getCui();
            columna[2] = clienteDAO.listaCliente().get(i).getNombres();
            columna[3] = clienteDAO.listaCliente().get(i).getDireccion();
            columna[4] = clienteDAO.listaCliente().get(i).getEstado();
            modeloTabla.addRow(columna);
        }
    }

    public void limpiarControles() {
        mantenimientoCliente.txtId.setText(null);
        mantenimientoCliente.txtCui.setText(null);
        mantenimientoCliente.txtNombre.setText(null);
        mantenimientoCliente.txtDireccion.setText(null);
        mantenimientoCliente.txtEstado.setText(null);
        mantenimientoCliente.txtId.requestFocus();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == mantenimientoCliente.btnAgregar) {
            insertarCliente();
        }
        if (e.getSource() == mantenimientoCliente.btnConsultar) {
            mostrarCliente();
        }
        if (e.getSource() == mantenimientoCliente.btnLimpiar) {
            limpiarControles();
        }
        if (e.getSource() == mantenimientoCliente.btnModificar) {
            modificarCliente();
        }
        if (e.getSource() == mantenimientoCliente.btnEliminar) {
            eliminarCliente();
        }
    }
}
