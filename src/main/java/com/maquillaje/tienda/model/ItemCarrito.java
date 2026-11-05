package com.maquillaje.tienda.model;

public class ItemCarrito {

    private int id;            // Identificador de la línea dentro del carrito
    private Producto producto;
    private String tono;       // null si el producto no tiene tonos
    private int cantidad;

    public ItemCarrito(int id, Producto producto, String tono, int cantidad) {
        this.id = id;
        this.producto = producto;
        this.tono = tono;
        this.cantidad = cantidad;
    }

    // Subtotal por producto = precio x cantidad
    public int getSubtotal() {
        return producto.getPrecio() * cantidad;
    }

    public int getId() {
        return id;
    }

    public Producto getProducto() {
        return producto;
    }

    public String getTono() {
        return tono;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}
