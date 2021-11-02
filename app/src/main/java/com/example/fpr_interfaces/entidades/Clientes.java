package com.example.fpr_interfaces.entidades;

public class Clientes {
    private String nomre;
    private String precio;
    private String descripcion;
    private int imagen;
    private String id_terapeuta;
    private String id_terapia;

    public String getId_terapeuta() {
        return id_terapeuta;
    }

    public void setId_terapeuta(String id_terapeuta) {
        this.id_terapeuta = id_terapeuta;
    }

    public String getId_terapia() {
        return id_terapia;
    }

    public void setId_terapia(String id_terapia) {
        this.id_terapia = id_terapia;
    }

    public String getNomre() {
        return nomre;
    }

    public void setNomre(String nomre) {
        this.nomre = nomre;
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

    public int getImagen() {
        return imagen;
    }

    public void setImagen(int imagen) {
        this.imagen = imagen;
    }
}
