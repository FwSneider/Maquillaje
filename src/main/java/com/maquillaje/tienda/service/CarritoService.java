package com.maquillaje.tienda.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;

import com.maquillaje.tienda.model.ItemCarrito;
import com.maquillaje.tienda.model.MetodoEnvio;
import com.maquillaje.tienda.model.Pedido;
import com.maquillaje.tienda.model.Producto;

@Service
public class CarritoService {

    // Lista en memoria con los productos del carrito
    private final List<ItemCarrito> items = new ArrayList<>();

    private int consecutivoItem = 1;
    private int consecutivoPedido = 1;

    // Método de envío elegido (por defecto, el primero de la lista)
    private MetodoEnvio envioSeleccionado;

    public CarritoService(EnvioService envioService) {
        this.envioSeleccionado = envioService.listarTodos().get(0);
    }

    // LISTAR
    public List<ItemCarrito> listarItems() {
        return items;
    }

    // AGREGAR: si ya existe el mismo producto con el mismo tono, suma la cantidad
    public void agregar(Producto producto, String tono, int cantidad) {
        if (cantidad < 1) {
            cantidad = 1;
        }
        // Si el producto no tiene tonos, no se guarda tono
        if (producto.getColores().isEmpty()) {
            tono = null;
        }
        for (ItemCarrito item : items) {
            if (item.getProducto().getId() == producto.getId()
                    && Objects.equals(item.getTono(), tono)) {
                item.setCantidad(item.getCantidad() + cantidad);
                return;
            }
        }
        items.add(new ItemCarrito(consecutivoItem++, producto, tono, cantidad));
    }

    // EDITAR cantidad
    public void actualizarCantidad(int idItem, int nuevaCantidad) {
        if (nuevaCantidad <= 0) {
            eliminar(idItem);
            return;
        }
        for (ItemCarrito item : items) {
            if (item.getId() == idItem) {
                item.setCantidad(nuevaCantidad);
                return;
            }
        }
    }

    // ELIMINAR
    public void eliminar(int idItem) {
        items.removeIf(item -> item.getId() == idItem);
    }

    public void seleccionarEnvio(MetodoEnvio metodo) {
        this.envioSeleccionado = metodo;
    }

    public MetodoEnvio getEnvioSeleccionado() {
        return envioSeleccionado;
    }

    public int contarUnidades() {
        int unidades = 0;
        for (ItemCarrito item : items) {
            unidades += item.getCantidad();
        }
        return unidades;
    }

    // CÁLCULO DE TOTALES
    public int calcularSubtotal() {
        int subtotal = 0;
        for (ItemCarrito item : items) {
            subtotal += item.getSubtotal();
        }
        return subtotal;
    }

    public int calcularTotal() {
        return calcularSubtotal() + envioSeleccionado.getCosto();
    }

    public boolean estaVacio() {
        return items.isEmpty();
    }

    // Genera el pedido con lo que hay en el carrito y lo vacía
    public Pedido generarPedido() {
        List<ItemCarrito> productosComprados = new ArrayList<>(items);

        Pedido pedido = new Pedido(
                consecutivoPedido,
                LocalDateTime.now(),
                productosComprados,
                envioSeleccionado,
                calcularSubtotal(),
                calcularTotal()
        );

        consecutivoPedido++;
        items.clear();

        return pedido;
    }
}
