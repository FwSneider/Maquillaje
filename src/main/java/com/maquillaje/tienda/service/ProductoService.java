package com.maquillaje.tienda.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.maquillaje.tienda.model.Marca;
import com.maquillaje.tienda.model.Producto;

@Service
public class ProductoService {

    // Lista en memoria (no hay base de datos)
    private final List<Producto> catalogo = new ArrayList<>();

    public ProductoService(MarcaService marcaService) {
        Marca dior = marcaService.buscarPorId(1);
        Marca atenea = marcaService.buscarPorId(2);
        Marca montoc = marcaService.buscarPorId(3);

        // Imágenes tomadas de los sitios de cada marca (o de una tienda que la vende).
        // Las dos que apuntan a /img/productos/ son archivos locales del proyecto.
        catalogo.add(new Producto(1, "Base líquida mate", 65000, "30 ml", "Rostro",
                List.of("Beige claro", "Natural", "Canela", "Moreno"),
                "https://www.dior.com/dw/image/v2/BGXS_PRD/on/demandware.static/-/Sites-master_dior/default/dwf0ed2e2f/Y0000150/Y0000150_E000001106_E01_GHC.jpg?sw=800",
                dior));
        catalogo.add(new Producto(2, "Corrector de ojeras", 38000, "6 ml", "Rostro",
                List.of("Claro", "Medio", "Oscuro"),
                "https://www.dior.com/dw/image/v2/BGXS_PRD/on/demandware.static/-/Sites-master_dior/default/dw35ec6dff/Y0326000/Y0326000_C032600020_E01_GHC.jpg?sw=800",
                dior));
        catalogo.add(new Producto(3, "Polvo compacto translúcido", 42000, "10 g", "Rostro",
                List.of(),
                "https://montoccosmetictools.com/cdn/shop/files/10g_5cd0a50f-2e4f-409c-8834-2d57182c61ed.jpg?width=600",
                montoc));
        catalogo.add(new Producto(4, "Rubor en polvo", 35000, "5 g", "Rostro",
                List.of("Rosado", "Durazno", "Coral"),
                "https://product-images.farmatodo.com/OTS1QCflYOYiQaMw2yJ5hpD3xWb3kp43Z9jcaiZLF9rbIkIcjCips3eV_n4ge-NV86sPqwuuWlaiiJQSQhXWjRpmniqTeFWjPYoH9P8rzfCKYLE=s360-rw",
                atenea));
        catalogo.add(new Producto(5, "Paleta de sombras 12 tonos", 85000, "12 g", "Ojos",
                List.of(),
                "https://montoccosmetictools.com/cdn/shop/files/Harmony-Iris-2.jpg?width=600",
                montoc));
        catalogo.add(new Producto(6, "Máscara de pestañas", 45000, "9 ml", "Ojos",
                List.of(),
                "https://product-images.farmatodo.com/RHN06JvYRrxIGa1LCyziMvaYxELzRq0Zkx7v17U2hq2ssf9ufF1ZKKNLu8kUlDyqi1sugLHnsHlqf4hI5vBCbHnIFamNO-KP1mrUjfJLlYF1ef0=s360-rw",
                atenea));
        catalogo.add(new Producto(7, "Delineador líquido", 32000, "3 ml", "Ojos",
                List.of("Negro"),
                "https://montoccosmetictools.com/cdn/shop/files/Delineador_en_gel_miau.webp?width=600",
                montoc));
        catalogo.add(new Producto(8, "Labial líquido mate", 38000, "5 ml", "Labios",
                List.of("Rojo", "Rosado", "Nude", "Vino"),
                "/img/productos/labial-liquido-mate.png",
                dior));
        catalogo.add(new Producto(9, "Brillo labial", 28000, "4 ml", "Labios",
                List.of("Transparente", "Rosa", "Coral"),
                "https://product-images.farmatodo.com/xGgM5QXQrpd9W6E8hhtc7lvsN4YBtDPLOx61G4NlLldqGrC_3ji-bx8-AjKSMQIFGyDsjuU75WfuMYoqoNQd4gEHd3IevnYE0GWsNCpJSgDQC7ew=s360-rw",
                atenea));
        catalogo.add(new Producto(10, "Esmalte de uñas", 18000, "12 ml", "Uñas",
                List.of("Rojo", "Rosa", "Nude", "Negro"),
                "/img/productos/esmalte-unas.png",
                dior));
    }

    public List<Producto> listarTodos() {
        return catalogo;
    }

    // Filtra el catálogo por categoría y/o marca (si no hay filtro devuelve todo)
    public List<Producto> filtrar(String categoria, Integer idMarca) {
        List<Producto> resultado = new ArrayList<>();
        for (Producto producto : catalogo) {
            boolean coincideCategoria = categoria == null || categoria.isBlank()
                    || producto.getCategoria().equals(categoria);
            boolean coincideMarca = idMarca == null || producto.getMarca().getId() == idMarca;
            if (coincideCategoria && coincideMarca) {
                resultado.add(producto);
            }
        }
        return resultado;
    }

    // Categorías distintas existentes en el catálogo
    public List<String> listarCategorias() {
        List<String> categorias = new ArrayList<>();
        for (Producto producto : catalogo) {
            if (!categorias.contains(producto.getCategoria())) {
                categorias.add(producto.getCategoria());
            }
        }
        return categorias;
    }

    public Producto buscarPorId(int id) {
        for (Producto producto : catalogo) {
            if (producto.getId() == id) {
                return producto;
            }
        }
        return null;
    }
}
