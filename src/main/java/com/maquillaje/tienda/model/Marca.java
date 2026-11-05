package com.maquillaje.tienda.model;

public class Marca {

    private int id;
    private String nombre;
    private String pais;
    private String descripcion;
    private String logo;

    public Marca(int id, String nombre, String pais, String descripcion, String logo) {
        this.id = id;
        this.nombre = nombre;
        this.pais = pais;
        this.descripcion = descripcion;
        this.logo = logo;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getPais() {
        return pais;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getLogo() {
        return logo;
    }
}
