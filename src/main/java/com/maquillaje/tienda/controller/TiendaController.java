package com.maquillaje.tienda.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.maquillaje.tienda.model.MetodoEnvio;
import com.maquillaje.tienda.model.Pedido;
import com.maquillaje.tienda.model.Producto;
import com.maquillaje.tienda.service.CarritoService;
import com.maquillaje.tienda.service.EnvioService;
import com.maquillaje.tienda.service.MarcaService;
import com.maquillaje.tienda.service.ProductoService;

@Controller
public class TiendaController {

    private final ProductoService productoService;
    private final MarcaService marcaService;
    private final EnvioService envioService;
    private final CarritoService carritoService;

    public TiendaController(ProductoService productoService,
                            MarcaService marcaService,
                            EnvioService envioService,
                            CarritoService carritoService) {
        this.productoService = productoService;
        this.marcaService = marcaService;
        this.envioService = envioService;
        this.carritoService = carritoService;
    }

    // Disponible en todas las vistas (contador del carrito en el encabezado)
    @ModelAttribute("unidadesCarrito")
    public int unidadesCarrito() {
        return carritoService.contarUnidades();
    }

    // Página principal: catálogo (con filtros por categoría y marca) y marcas
    @GetMapping("/")
    public String catalogo(@RequestParam(required = false) String categoria,
                           @RequestParam(required = false) Integer marca,
                           Model model) {
        model.addAttribute("productos", productoService.filtrar(categoria, marca));
        model.addAttribute("categorias", productoService.listarCategorias());
        model.addAttribute("marcas", marcaService.listarTodas());
        model.addAttribute("categoriaSeleccionada", categoria);
        model.addAttribute("marcaSeleccionada", marca);
        return "index";
    }

    // Agregar al carrito (con tono y cantidad)
    @PostMapping("/carrito/agregar/{id}")
    public String agregar(@PathVariable int id,
                          @RequestParam(defaultValue = "1") int cantidad,
                          @RequestParam(required = false) String tono,
                          @RequestParam(required = false) String categoria,
                          @RequestParam(required = false) String marca,
                          RedirectAttributes redirect) {
        Producto producto = productoService.buscarPorId(id);
        if (producto != null) {
            carritoService.agregar(producto, tono, cantidad);
            redirect.addFlashAttribute("mensaje", producto.getNombre() + " se agregó al carrito");
        }
        // Conserva los filtros que tenía el usuario
        if (categoria != null && !categoria.isBlank()) {
            redirect.addAttribute("categoria", categoria);
        }
        if (marca != null && !marca.isBlank()) {
            redirect.addAttribute("marca", marca);
        }
        return "redirect:/";
    }

    // Carrito
    @GetMapping("/carrito")
    public String verCarrito(Model model) {
        model.addAttribute("items", carritoService.listarItems());
        model.addAttribute("metodosEnvio", envioService.listarTodos());
        model.addAttribute("envioSeleccionado", carritoService.getEnvioSeleccionado());
        model.addAttribute("subtotal", carritoService.calcularSubtotal());
        model.addAttribute("total", carritoService.calcularTotal());
        model.addAttribute("vacio", carritoService.estaVacio());
        return "carrito";
    }

    // Editar cantidad
    @PostMapping("/carrito/actualizar/{id}")
    public String actualizar(@PathVariable int id, @RequestParam int cantidad) {
        carritoService.actualizarCantidad(id, cantidad);
        return "redirect:/carrito";
    }

    // Eliminar producto del carrito
    @PostMapping("/carrito/eliminar/{id}")
    public String eliminar(@PathVariable int id) {
        carritoService.eliminar(id);
        return "redirect:/carrito";
    }

    // Seleccionar método de envío
    @PostMapping("/carrito/envio")
    public String seleccionarEnvio(@RequestParam int idEnvio) {
        MetodoEnvio metodo = envioService.buscarPorId(idEnvio);
        if (metodo != null) {
            carritoService.seleccionarEnvio(metodo);
        }
        return "redirect:/carrito";
    }

    // Generar pedido (factura)
    @PostMapping("/pedido")
    public String generarPedido(Model model) {
        if (carritoService.estaVacio()) {
            return "redirect:/carrito";
        }
        Pedido pedido = carritoService.generarPedido();
        model.addAttribute("pedido", pedido);

        // El carrito quedó vacío: se actualiza el contador del encabezado
        model.addAttribute("unidadesCarrito", 0);

        return "factura";
    }
}
