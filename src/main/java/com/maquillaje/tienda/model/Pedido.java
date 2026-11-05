package com.maquillaje.tienda.model;

import java.time.LocalDateTime;
import java.util.List;

public class Pedido {

    private int numero;
    private LocalDateTime fecha;
    private List<ItemCarrito> items;
    private MetodoEnvio metodoEnvio;
    private int subtotal;   // Suma de los subtotales de los productos
    private int total;      // Subtotal + envío

    public Pedido(int numero, LocalDateTime fecha, List<ItemCarrito> items,
                  MetodoEnvio metodoEnvio, int subtotal, int total) {
        this.numero = numero;
        this.fecha = fecha;
        this.items = items;
        this.metodoEnvio = metodoEnvio;
        this.subtotal = subtotal;
        this.total = total;
    }

    public int getNumero() {
        return numero;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public List<ItemCarrito> getItems() {
        return items;
    }

    public MetodoEnvio getMetodoEnvio() {
        return metodoEnvio;
    }

    public int getSubtotal() {
        return subtotal;
    }

    public int getTotal() {
        return total;
    }
}
