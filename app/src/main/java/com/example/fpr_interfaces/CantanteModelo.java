package com.example.fpr_interfaces;

import java.io.Serializable;

public class CantanteModelo implements Serializable {
    private String cantante,nacionalidad;
    private int fotocantante;

    public CantanteModelo() {
    }

    public CantanteModelo(String cantante, String nacionalidad, int fotocantante) {
        this.cantante = cantante;
        this.nacionalidad = nacionalidad;
        this.fotocantante = fotocantante;
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
