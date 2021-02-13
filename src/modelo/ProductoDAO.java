package modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Adin Rubio
 */
public class ProductoDAO extends Conexion {

    private PreparedStatement ps;
    private ResultSet rs;
    private String sql;
    private String respuesta;

    public String createProducto(Producto producto) {
        try {
            this.conectar();
            sql = "INSERT INTO producto (idProducto, Nombres, Precio, Stock, Estado) values (?,?,?,?,?)";
            ps = getMiConexion().prepareStatement(sql);
            ps.setInt(1, producto.getIdProducto());
            ps.setString(2, producto.getNombres());
            ps.setDouble(3, producto.getPrecio());
            ps.setInt(4, producto.getStock());
            ps.setString(5, producto.getEstado());
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

    public Producto readProducto(int id) {
        Producto producto = new Producto();
        try {
            this.conectar();
            sql = "SELECT * FROM producto WHERE idProducto = ?";
            ps = getMiConexion().prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            rs.next();
            producto.setIdProducto(rs.getInt("idProducto"));
            producto.setNombres(rs.getString("Nombres"));
            producto.setPrecio(rs.getDouble("Precio"));
            producto.setStock(rs.getInt("Stock"));
            producto.setEstado(rs.getString("Estado"));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            this.cerrarConexion();
        }
        return producto;
    }

    public String updateProducto(Producto producto) {
        try {
            this.conectar();
            sql = "UPDATE producto SET Nombres = ?, Precio = ?, Stock = ?, Estado = ? WHERE idProducto = ?";
            ps = getMiConexion().prepareStatement(sql);
            ps.setString(1, producto.getNombres());
            ps.setDouble(2, producto.getPrecio());
            ps.setInt(3, producto.getStock());
            ps.setString(4, producto.getEstado());
            ps.setInt(5, producto.getIdProducto());
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

    public String deleteProducto(int id) {
        try {
            this.conectar();
            sql = "DELETE FROM producto WHERE idProducto = ?";
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

    public ArrayList<Producto> listaProducto() {
        ArrayList<Producto> lista = null;
        try {
            this.conectar();
            sql = "SELECT * FROM producto";
            ps = this.getMiConexion().prepareStatement(sql);
            rs = ps.executeQuery();
            lista = new ArrayList();
            while (rs.next()) {
                Producto producto = new Producto();
                producto.setIdProducto(rs.getInt("idProducto"));
                producto.setNombres(rs.getString("Nombres"));
                producto.setPrecio(rs.getDouble("Precio"));
                producto.setStock(rs.getInt("Stock"));
                producto.setEstado(rs.getString("Estado"));
                lista.add(producto);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            this.cerrarConexion();
        }
        return lista;
    }

    public String updateStock(int cantidad, int id) {
        try {
            this.conectar();
            sql = "UPDATE producto SET Stock = ? WHERE idProducto = ?";
            ps = getMiConexion().prepareStatement(sql);
            ps.setInt(1, cantidad);
            ps.setInt(2, id);
            int r = ps.executeUpdate();
            if (r == 1) {
                respuesta = "Stock actualizado.";
            } else {
                respuesta = "Producto no encontrado.";
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            respuesta = "No se actualizo el Stock.";
        } finally {
            this.cerrarConexion();
        }
        return respuesta;
    }

}
