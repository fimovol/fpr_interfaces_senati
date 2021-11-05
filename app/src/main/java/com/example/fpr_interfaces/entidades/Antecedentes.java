package com.example.fpr_interfaces.entidades;

public class Antecedentes {
    private String usuario;
    private String id_clientefk;

    public String getId_clientefk() {
        return id_clientefk;
    }

    public void setId_clientefk(String id_clientefk) {
        this.id_clientefk = id_clientefk;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
}
