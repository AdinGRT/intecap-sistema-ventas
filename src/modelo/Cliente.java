package modelo;

/**
 *
 * @author Adin Rubio
 */
public class Cliente {
    private int idCliente;
    private String cui;
    private String nombres;
    private String direccion;
    private String estado;

    public Cliente() {
    }

    public Cliente(int idCliente, String cui, String nombres, String direccion, String estado) {
        this.idCliente = idCliente;
        this.cui = cui;
        this.nombres = nombres;
        this.direccion = direccion;
        this.estado = estado;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getCui() {
        return cui;
    }

    public void setCui(String cui) {
        this.cui = cui;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    
}
