package com.example.fpr_interfaces.entidades;

public class Terapiasentidad {
    private String nombre;
    private String precio;
    private String descripcion;
    private String id_terapia;

    public String getId_terapia() {
        return id_terapia;
    }

    public void setId_terapia(String id_terapia) {
        this.id_terapia = id_terapia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
