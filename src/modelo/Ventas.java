package modelo;

/**
 *
 * @author Adin Rubio
 */
public class Ventas {
    private int idVentas;
    private String numeroVentas;
    private String fechaVentas;
    private double monto;
    private String estado;
    private int cliente_idCliente;
    private int vendedor_idVendedor;    

    public Ventas() {
    }

    public Ventas(int idVenta, String numeroVenta, String fechaVenta, double monto, String estado, int idCliente, int idProducto) {
        this.idVentas = idVenta;
        this.numeroVentas = numeroVenta;
        this.fechaVentas = fechaVenta;
        this.monto = monto;
        this.estado = estado;
        this.cliente_idCliente = idCliente;
        this.vendedor_idVendedor = idProducto;
    }

    public int getIdVentas() {
        return idVentas;
    }

    public void setIdVentas(int idVentas) {
        this.idVentas = idVentas;
    }

    public String getNumeroVentas() {
        return numeroVentas;
    }

    public void setNumeroVentas(String numeroVentas) {
        this.numeroVentas = numeroVentas;
    }

    public String getFechaVentas() {
        return fechaVentas;
    }

    public void setFechaVentas(String fechaVentas) {
        this.fechaVentas = fechaVentas;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getCliente_idCliente() {
        return cliente_idCliente;
    }

    public void setCliente_idCliente(int cliente_idCliente) {
        this.cliente_idCliente = cliente_idCliente;
    }  
    
    public int getVendedor_idVendedor() {
        return vendedor_idVendedor;
    }

    public void setVendedor_idVendedor(int vendedor_idVendedor) {
        this.vendedor_idVendedor = vendedor_idVendedor;
    }
    
}
