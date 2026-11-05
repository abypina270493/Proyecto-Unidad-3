package mx.itson.vivario.entidades;

import java.util.Date;

public class Planta {
    private int id;
    private String nombreComun;
    private String nombreCient;
    private String tipo;
    private int riegoDias;
    private String luz;
    private double precio;
    private Date fechaRegistro;

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNombreComun() { return nombreComun; }
    public void setNombreComun(String nombreComun) { this.nombreComun = nombreComun; }
    public String getNombreCient() { return nombreCient; }
    public void setNombreCient(String nombreCient) { this.nombreCient = nombreCient; }
    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
    public int getRiegoDias() { return riegoDias; }
    public void setRiegoDias(int riegoDias) { this.riegoDias = riegoDias; }
    public String getLuz() { return luz; }
    public void setLuz(String luz) { this.luz = luz; }
    public double getPrecio() { return precio; }
    public void setPrecio(double precio) { this.precio = precio; }
    public Date getFechaRegistro() { return fechaRegistro; }
    public void setFechaRegistro(Date fechaRegistro) { this.fechaRegistro = fechaRegistro; }
}
