package modelo;

/**
 *
 * @author Adin Rubio
 */
public class Vendedor {
    private int idVendedor;
    private String cui;
    private String nombres;
    private String telefono;
    private String estado;
    private String usuario;

    public Vendedor() {
    }

    public Vendedor(int idVendedor, String cui, String nombres, String telefono, String estado, String usuario) {
        this.idVendedor = idVendedor;
        this.cui = cui;
        this.nombres = nombres;
        this.telefono = telefono;
        this.estado = estado;
        this.usuario = usuario;
    }

    public int getIdVendedor() {
        return idVendedor;
    }

    public void setIdVendedor(int idVendedor) {
        this.idVendedor = idVendedor;
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

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
    
    
}

