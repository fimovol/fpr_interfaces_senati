package com.example.fpr_interfaces;

import java.io.Serializable;

public class CantanteModelo implements Serializable {
    private String cantante,nacionalidad,precio;
    private int fotocantante;

    public CantanteModelo() {
    }


    public CantanteModelo(String cantante, String nacionalidad, int fotocantante, String precio) {
        this.cantante = cantante;
        this.nacionalidad = nacionalidad;
        this.fotocantante = fotocantante;
        this.precio = precio;
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
