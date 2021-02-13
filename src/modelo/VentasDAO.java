package modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Adin Rubio
 */
public class VentasDAO extends Conexion{
    private PreparedStatement ps;
    private ResultSet rs;
    private String sql;
    private int respuesta;
    Ventas modeloVenta = new Ventas();
    
    public int guardarVentas(Ventas ventas) {
        try {
            this.conectar();
            sql = "INSERT INTO ventas (idVentas, NumeroVentas, FechaVentas, Monto, Estado, Cliente_idCliente, Vendedor_idVendedor) values (?,?,?,?,?,?,?)";
            ps = getMiConexion().prepareStatement(sql);
            ps.setInt(1, ventas.getIdVentas());
            ps.setString(2, ventas.getNumeroVentas());
            ps.setString(3, ventas.getFechaVentas());
            ps.setDouble(4, ventas.getMonto());
            ps.setString(5, ventas.getEstado());
            ps.setInt(6, ventas.getCliente_idCliente());
            ps.setInt(7, ventas.getVendedor_idVendedor());
            respuesta = ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            
        } finally {
            this.cerrarConexion();
        }        
        return respuesta;
    }
    
    public int guardarDetalleVentas(DetalleVentas detalleVentas) {
        try {
            this.conectar();
            sql = "INSERT INTO detalle_ventas (idDetalleVentas, Ventas_idVentas, Producto_idProducto, Cantidad, PrecioVenta) values (?,?,?,?,?)";
            ps = getMiConexion().prepareStatement(sql);
            ps.setInt(1, detalleVentas.getIdDetalleVentas());
            ps.setInt(2, detalleVentas.getVentas_idVentas());
            ps.setInt(3, detalleVentas.getProducto_idProducto());
            ps.setInt(4, detalleVentas.getCantidad());
            ps.setDouble(5, detalleVentas.getPrecioVenta());
            respuesta = ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            this.cerrarConexion();
        }        
        return respuesta;
    }

    public String NoSerieVentas() {
        String serie = "";
        try {
            this.conectar();
            sql = "SELECT MAX(idVentas) FROM Ventas";
            ps = getMiConexion().prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                serie = rs.getString(1);
            }            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            this.cerrarConexion();
        }
        return serie;
    }
    
}
