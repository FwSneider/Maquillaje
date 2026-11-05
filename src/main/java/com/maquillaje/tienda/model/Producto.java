package com.maquillaje.tienda.model;

import java.util.List;

public class Producto {

    private int id;
    private String nombre;
    private int precio;
    private String contenidoNeto;   // Ej: "30 ml" o "10 g"
    private String categoria;
    private List<String> colores;   // Vacía si el producto no tiene tonos
    private String imagen;
    private Marca marca;

    public Producto(int id, String nombre, int precio, String contenidoNeto,
                    String categoria, List<String> colores, String imagen, Marca marca) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.contenidoNeto = contenidoNeto;
        this.categoria = categoria;
        this.colores = colores;
        this.imagen = imagen;
        this.marca = marca;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public int getPrecio() {
        return precio;
    }

    public String getContenidoNeto() {
        return contenidoNeto;
    }

    public String getCategoria() {
        return categoria;
    }

    public List<String> getColores() {
        return colores;
    }

    public String getImagen() {
        return imagen;
    }

    public Marca getMarca() {
        return marca;
    }
}
