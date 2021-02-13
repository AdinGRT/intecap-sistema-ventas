package modelo;

/**
 *
 * @author Adin Rubio
 */
public class DetalleVentas {

    private int idDetalleVentas;
    private int ventas_idVentas;
    private int producto_idProducto;
    private int cantidad;
    private double precioVenta;

    public DetalleVentas() {
    }

    public DetalleVentas(int idDetalleVentas, int ventas_idVentas, int producto_idProducto, int cantidad, double precioVenta) {
        this.idDetalleVentas = idDetalleVentas;
        this.ventas_idVentas = ventas_idVentas;
        this.producto_idProducto = producto_idProducto;
        this.cantidad = cantidad;
        this.precioVenta = precioVenta;
    }

    public int getIdDetalleVentas() {
        return idDetalleVentas;
    }

    public void setIdDetalleVentas(int idDetalleVentas) {
        this.idDetalleVentas = idDetalleVentas;
    }
    
    public int getVentas_idVentas() {
        return ventas_idVentas;
    }

    public void setVentas_idVentas(int ventas_idVentas) {
        this.ventas_idVentas = ventas_idVentas;
    }

    public int getProducto_idProducto() {
        return producto_idProducto;
    }

    public void setProducto_idProducto(int producto_idProducto) {
        this.producto_idProducto = producto_idProducto;
    }

    
    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(double precioVenta) {
        this.precioVenta = precioVenta;
    }

}
