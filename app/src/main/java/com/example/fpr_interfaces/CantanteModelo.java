package com.example.fpr_interfaces;

import java.io.Serializable;

public class CantanteModelo implements Serializable {
    private String cantante,nacionalidad,precio,id_terapia;
    private int fotocantante;

    public CantanteModelo() {
    }


    public CantanteModelo(String cantante, String nacionalidad, int fotocantante, String precio,String id_terapia) {
        this.cantante = cantante;
        this.nacionalidad = nacionalidad;
        this.fotocantante = fotocantante;
        this.precio = precio;
        this.id_terapia = id_terapia;
    }

    public String getId_terapia() {
        return id_terapia;
    }

    public void setId_terapia(String id_terapia) {
        this.id_terapia = id_terapia;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getCantante() {
        return cantante;
    }

    public void setCantante(String cantante) {
        this.cantante = cantante;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public int getFotocantante() {
        return fotocantante;
    }

    public void setFotocantante(int fotocantante) {
        this.fotocantante = fotocantante;
    }
}
