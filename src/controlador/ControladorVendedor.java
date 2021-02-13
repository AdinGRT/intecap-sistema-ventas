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
public class ControladorVendedor implements ActionListener {

    LoginFormulario vistaLogin = new LoginFormulario();
    VendedorDAO vendedorDAO = new VendedorDAO();
    Vendedor modeloVendedor = new Vendedor();
    PrincipalFormulario principalFormulario = new PrincipalFormulario();
    MantenimientoVendedor mantenimientoVendedor = new MantenimientoVendedor();

    public ControladorVendedor(LoginFormulario vistaLogin) {
        this.vistaLogin = vistaLogin;
        this.vistaLogin.btnLogIn.addActionListener(this);
    }

    public ControladorVendedor(MantenimientoVendedor mantenimientoVendedor) {
        this.mantenimientoVendedor = mantenimientoVendedor;
        this.mantenimientoVendedor.btnAgregar.addActionListener(this);
        this.mantenimientoVendedor.btnConsultar.addActionListener(this);
        this.mantenimientoVendedor.btnLimpiar.addActionListener(this);
        this.mantenimientoVendedor.btnModificar.addActionListener(this);
        this.mantenimientoVendedor.btnEliminar.addActionListener(this);
        llenarTabla(this.mantenimientoVendedor.tablaVendedor);
    }

    public void validar() {
        String usuario = vistaLogin.txtUser.getText();
        String cui = vistaLogin.txtPassword.getText();
        if (usuario.equals("") || cui.equals("")) {
            JOptionPane.showMessageDialog(null, "No puede dejar en blanco usuario y contraseña");
        } else {
            modeloVendedor = vendedorDAO.ValidarVendedor(cui, usuario);
            if ((modeloVendedor.getUsuario() != null) && (modeloVendedor.getCui() != null)) {
                principalFormulario.setVisible(true);
                principalFormulario.setUsuario(usuario);
                principalFormulario.setIdUsuario(modeloVendedor.getIdVendedor());
                principalFormulario.labelUsuario.setText(usuario);
                vistaLogin.dispose();
            } else {
                JOptionPane.showMessageDialog(null, "Ingrese usuario o contrasenia valida!");
                vistaLogin.txtUser.requestFocus();
            }
        }
    }

    public void insertarVendedor() {
        if (mantenimientoVendedor.txtId.getText().equals("") || mantenimientoVendedor.txtCui.getText().equals("")
                || mantenimientoVendedor.txtNombre.getText().equals("") || mantenimientoVendedor.txtTelefono.getText().equals("")
                || mantenimientoVendedor.txtEstado.getText().equals("") || mantenimientoVendedor.txtUsuario.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Uno o mas campos están vacíos. Llenar!");
        } else {
            try {
                modeloVendedor.setIdVendedor(Integer.parseInt(mantenimientoVendedor.txtId.getText()));
                modeloVendedor.setCui(mantenimientoVendedor.txtCui.getText());
                modeloVendedor.setNombres(mantenimientoVendedor.txtNombre.getText());
                modeloVendedor.setTelefono(mantenimientoVendedor.txtTelefono.getText());
                modeloVendedor.setEstado(mantenimientoVendedor.txtEstado.getText());
                modeloVendedor.setUsuario(mantenimientoVendedor.txtUsuario.getText());
                String respuesta = vendedorDAO.createVendedor(modeloVendedor);
                if (respuesta != null) {
                    JOptionPane.showMessageDialog(null, respuesta);
                    limpiarControles();
                    llenarTabla(this.mantenimientoVendedor.tablaVendedor);
                }
            } catch (NumberFormatException e) {
                System.out.println(e);
                JOptionPane.showMessageDialog(null, "Debe ingresar un número en Id.");
                limpiarControles();
            }
        }
    }

    public void mostrarVendedor() {
        if (mantenimientoVendedor.txtId.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Ingrese un Id!");
        } else {
            try {
                int valor = Integer.parseInt(mantenimientoVendedor.txtId.getText());
                modeloVendedor = vendedorDAO.readVendedor(valor);
                mantenimientoVendedor.txtCui.setText(modeloVendedor.getCui());
                mantenimientoVendedor.txtNombre.setText(modeloVendedor.getNombres());
                mantenimientoVendedor.txtTelefono.setText(modeloVendedor.getTelefono());
                mantenimientoVendedor.txtEstado.setText(modeloVendedor.getEstado());
                mantenimientoVendedor.txtUsuario.setText(modeloVendedor.getUsuario());
                if (modeloVendedor.getIdVendedor() == 0) {
                    JOptionPane.showMessageDialog(null, "Registro inválido!");
                    limpiarControles();
                    llenarTabla(this.mantenimientoVendedor.tablaVendedor);
                }
            } catch (NumberFormatException e) {
                System.out.println(e);
                JOptionPane.showMessageDialog(null, "Debe ingresar un número en Id.");
                limpiarControles();
            }
        }
    }

    public void modificarVendedor() {
        if (mantenimientoVendedor.txtId.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Ingrese un Id!");
        } else {
            try {
                modeloVendedor.setIdVendedor(Integer.parseInt(mantenimientoVendedor.txtId.getText()));
                modeloVendedor.setCui(mantenimientoVendedor.txtCui.getText());
                modeloVendedor.setNombres(mantenimientoVendedor.txtNombre.getText());
                modeloVendedor.setTelefono(mantenimientoVendedor.txtTelefono.getText());
                modeloVendedor.setEstado(mantenimientoVendedor.txtEstado.getText());
                modeloVendedor.setUsuario(mantenimientoVendedor.txtUsuario.getText());
                String respuestaActualizar = this.vendedorDAO.updateVendedor(modeloVendedor);
                if (respuestaActualizar != null) {
                    JOptionPane.showMessageDialog(null, respuestaActualizar);
                    limpiarControles();
                    llenarTabla(this.mantenimientoVendedor.tablaVendedor);
                }
            } catch (NumberFormatException e) {
                System.out.println(e);
                JOptionPane.showMessageDialog(null, "Uno o mas campos tienen un formato inválido.");
                limpiarControles();
            }
        }
    }

    public void eliminarVendedor() {
        if (mantenimientoVendedor.txtId.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Ingrese un Id!");
        } else {
            try {
                modeloVendedor.setIdVendedor(Integer.parseInt(mantenimientoVendedor.txtId.getText()));
                String respuestaEliminar = this.vendedorDAO.deleteVendedor(modeloVendedor.getIdVendedor());
                if (respuestaEliminar != null) {
                    JOptionPane.showMessageDialog(null, respuestaEliminar);
                    limpiarControles();
                    llenarTabla(this.mantenimientoVendedor.tablaVendedor);
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
        modeloTabla.addColumn("Telefono");
        modeloTabla.addColumn("Estado");
        modeloTabla.addColumn("Usuario");
        Object[] columna = new Object[6];
        int numRegistro = vendedorDAO.listaVendedor().size();
        for (int i = 0; i < numRegistro; i++) {
            columna[0] = vendedorDAO.listaVendedor().get(i).getIdVendedor();
            columna[1] = vendedorDAO.listaVendedor().get(i).getCui();
            columna[2] = vendedorDAO.listaVendedor().get(i).getNombres();
            columna[3] = vendedorDAO.listaVendedor().get(i).getTelefono();
            columna[4] = vendedorDAO.listaVendedor().get(i).getEstado();
            columna[5] = vendedorDAO.listaVendedor().get(i).getUsuario();
            modeloTabla.addRow(columna);
        }
    }

    public void limpiarControles() {
        mantenimientoVendedor.txtId.setText(null);
        mantenimientoVendedor.txtCui.setText(null);
        mantenimientoVendedor.txtNombre.setText(null);
        mantenimientoVendedor.txtTelefono.setText(null);
        mantenimientoVendedor.txtEstado.setText(null);
        mantenimientoVendedor.txtUsuario.setText(null);
        mantenimientoVendedor.txtId.requestFocus();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vistaLogin.btnLogIn) {
            validar();
        }
        if (e.getSource() == mantenimientoVendedor.btnAgregar) {
            insertarVendedor();
        }
        if (e.getSource() == mantenimientoVendedor.btnConsultar) {
            mostrarVendedor();
        }
        if (e.getSource() == mantenimientoVendedor.btnLimpiar) {
            limpiarControles();
        }
        if (e.getSource() == mantenimientoVendedor.btnModificar) {
            modificarVendedor();
        }
        if (e.getSource() == mantenimientoVendedor.btnEliminar) {
            eliminarVendedor();
        }
    }

}
