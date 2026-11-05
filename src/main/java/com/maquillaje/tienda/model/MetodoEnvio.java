package com.maquillaje.tienda.model;

public class MetodoEnvio {

    private int id;
    private String nombre;
    private int costo;
    private String tiempoEntrega;

    public MetodoEnvio(int id, String nombre, int costo, String tiempoEntrega) {
        this.id = id;
        this.nombre = nombre;
        this.costo = costo;
        this.tiempoEntrega = tiempoEntrega;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public int getCosto() {
        return costo;
    }

    public String getTiempoEntrega() {
        return tiempoEntrega;
    }
}
