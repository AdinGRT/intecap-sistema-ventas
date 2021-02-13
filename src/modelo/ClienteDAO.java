package modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Adin Rubio
 */
public class ClienteDAO extends Conexion {

    private PreparedStatement ps;
    private ResultSet rs;
    private String sql;
    private String respuesta;

    public String createCliente(Cliente cliente) {
        try {
            this.conectar();
            sql = "INSERT INTO cliente (idCliente, Cui, Nombres, Direccion, Estado) values (?,?,?,?,?)";
            ps = getMiConexion().prepareStatement(sql);
            ps.setInt(1, cliente.getIdCliente());
            ps.setString(2, cliente.getCui());
            ps.setString(3, cliente.getNombres());
            ps.setString(4, cliente.getDireccion());
            ps.setString(5, cliente.getEstado());
            int r = ps.executeUpdate();
            if (r == 1) {
                respuesta = "Registro almacenado correctamente!";
            } else {
                respuesta = "Registro no encontrado.";
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            respuesta = "No se puede almacenar el regitro!";
        } finally {
            this.cerrarConexion();
        }
        return respuesta;
    }

    public Cliente readCliente(int id) {
        Cliente cliente = new Cliente();
        try {
            this.conectar();
            sql = "SELECT * FROM cliente WHERE idCliente = ?";
            ps = getMiConexion().prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            rs.next();
            cliente.setIdCliente(rs.getInt("idCliente"));
            cliente.setCui(rs.getString("Cui"));
            cliente.setNombres(rs.getString("Nombres"));
            cliente.setDireccion(rs.getString("Direccion"));
            cliente.setEstado(rs.getString("Estado"));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            this.cerrarConexion();
        }
        return cliente;
    }

    public String updateCliente(Cliente cliente) {
        try {
            this.conectar();
            sql = "UPDATE cliente SET Cui = ?, Nombres = ?, Direccion = ?, Estado = ? WHERE idCliente = ?";
            ps = getMiConexion().prepareStatement(sql);
            System.out.println(ps);
            ps.setString(1, cliente.getCui());
            ps.setString(2, cliente.getNombres());
            ps.setString(3, cliente.getDireccion());
            ps.setString(4, cliente.getEstado());
            ps.setInt(5, cliente.getIdCliente());
            int r = ps.executeUpdate();
            if (r == 1) {
                respuesta = "Registro modificado correctamente!";
            } else {
                respuesta = "Registro no encontrado.";
            }
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            respuesta = "No se puede modificar el registro!";
        } finally {
            this.cerrarConexion();
        }
        return respuesta;
    }

    public String deleteCliente(int id) {
        try {
            this.conectar();
            sql = "DELETE FROM cliente WHERE idCliente = ?";
            ps = getMiConexion().prepareStatement(sql);
            ps.setInt(1, id);
            int r = ps.executeUpdate();
            if (r == 1) {
                respuesta = "Registro eliminado correctamente!";
            } else {
                respuesta = "Registro no encontrado.";
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            respuesta = "No se ha podido eliminar el registro";
        } finally {
            this.cerrarConexion();
        }
        return respuesta;
    }
    
    public ArrayList<Cliente> listaCliente(){
        ArrayList<Cliente> lista = null;
        try {
            this.conectar();
            sql = "SELECT * FROM cliente";
            ps = this.getMiConexion().prepareStatement(sql);
            rs = ps.executeQuery();
            lista = new ArrayList();
            while(rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setIdCliente(rs.getInt("idCliente"));
                cliente.setCui(rs.getString("Cui"));
                cliente.setNombres(rs.getString("Nombres"));
                cliente.setDireccion(rs.getString("Direccion"));
                cliente.setEstado(rs.getString("Estado"));
                lista.add(cliente);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            this.cerrarConexion();
        }
        return lista;
    }

    public Cliente searchCliente(String cui) {
        Cliente cliente = new Cliente();
        try {
            this.conectar();
            sql = "SELECT * FROM cliente WHERE Cui = ?";
            ps = getMiConexion().prepareStatement(sql);
            ps.setString(1, cui);
            rs = ps.executeQuery();
            rs.next();
            cliente.setIdCliente(rs.getInt("idCliente"));
            cliente.setCui(rs.getString("Cui"));
            cliente.setNombres(rs.getString("Nombres"));
            cliente.setDireccion(rs.getString("Direccion"));
            cliente.setEstado(rs.getString("Estado"));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            this.cerrarConexion();
        }
        return cliente;
    }
}
