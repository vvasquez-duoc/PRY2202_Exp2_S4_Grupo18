package com.duoc.biblioteca.entidades.usuarios;

public class Usuarios {
    private String rut;
    private String nombreCompleto;
    private String direccion;
    private String comuna;

    public Usuarios(String rut, String nombreCompleto, String direccion, String comuna){
        this.rut = rut;
        this.nombreCompleto = nombreCompleto;
        this.direccion = direccion;
        this.comuna = comuna;
    }

    public String getRut() {
        return rut;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getComuna() {
        return comuna;
    }
}