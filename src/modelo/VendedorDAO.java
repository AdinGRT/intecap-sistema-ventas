package modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Adin Rubio
 */
public class VendedorDAO extends Conexion {    
    private PreparedStatement ps;
    private ResultSet rs;
    private String sql;
    private String respuesta;
    
    public Vendedor ValidarVendedor(String cui, String usuario){
        Vendedor vendedor = new Vendedor();
        try {
            this.conectar();
            sql = "SELECT * FROM vendedor WHERE Cui = ? AND Usuario = ?";
            ps = getMiConexion().prepareStatement(sql);
            ps.setString(1, cui);
            ps.setString(2, usuario);
            rs = ps.executeQuery();
            while (rs.next()) {
                vendedor.setIdVendedor(rs.getInt("IdVendedor"));
                vendedor.setCui(rs.getString("Cui"));
                vendedor.setNombres(rs.getString("Nombres"));
                vendedor.setTelefono(rs.getString("Telefono"));
                vendedor.setEstado(rs.getString("Estado"));
                vendedor.setUsuario(rs.getString("Usuario"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            this.cerrarConexion();
        }
        return vendedor;
    }
    
    public String createVendedor(Vendedor vendedor) {
        try {
            this.conectar();
            sql = "INSERT INTO vendedor (idVendedor, Cui, Nombres, Telefono, Estado, Usuario) values (?,?,?,?,?,?)";
            ps = getMiConexion().prepareStatement(sql);
            ps.setInt(1, vendedor.getIdVendedor());
            ps.setString(2, vendedor.getCui());
            ps.setString(3, vendedor.getNombres());
            ps.setString(4, vendedor.getTelefono());
            ps.setString(5, vendedor.getEstado());
            ps.setString(6, vendedor.getUsuario());
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
    
    public Vendedor readVendedor(int id) {
        Vendedor vendedor = new Vendedor();
        try {
            this.conectar();
            sql = "SELECT * FROM vendedor WHERE idVendedor = ?";
            ps = getMiConexion().prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            rs.next();
            vendedor.setIdVendedor(rs.getInt("idVendedor"));
            vendedor.setCui(rs.getString("Cui"));
            vendedor.setNombres(rs.getString("Nombres"));
            vendedor.setTelefono(rs.getString("Telefono"));
            vendedor.setEstado(rs.getString("Estado"));
            vendedor.setUsuario(rs.getString("Usuario"));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            this.cerrarConexion();
        }
        return vendedor;
    }
    
    public String updateVendedor (Vendedor vendedor) {
        try {
            this.conectar();
            sql = "UPDATE vendedor SET Cui = ?, Nombres = ?, Telefono = ?, Estado = ?, Usuario = ? WHERE idVendedor = ?";
            ps = getMiConexion().prepareStatement(sql);
            ps.setString(1, vendedor.getCui());
            ps.setString(2, vendedor.getNombres());
            ps.setString(3, vendedor.getTelefono());
            ps.setString(4, vendedor.getEstado());
            ps.setString(5, vendedor.getUsuario());
            ps.setInt(6, vendedor.getIdVendedor());
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
    
    public String deleteVendedor (int id) {
        try {
            this.conectar();
            sql = "DELETE FROM vendedor WHERE idVendedor = ?";
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

    public ArrayList<Vendedor> listaVendedor() {
        ArrayList<Vendedor> lista = null;
        try {
            this.conectar();
            sql = "SELECT * FROM vendedor";
            ps = this.getMiConexion().prepareStatement(sql);
            rs = ps.executeQuery();
            lista = new ArrayList();
            while (rs.next()) {
                Vendedor vendedor = new Vendedor();
                vendedor.setIdVendedor(rs.getInt("idVendedor"));
                vendedor.setCui(rs.getString("Cui"));
                vendedor.setNombres(rs.getString("Nombres"));
                vendedor.setTelefono(rs.getString("Telefono"));                
                vendedor.setEstado(rs.getString("Estado"));
                vendedor.setUsuario(rs.getString("Usuario"));
                lista.add(vendedor);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            this.cerrarConexion();
        }
        return lista;
    }
    
}

