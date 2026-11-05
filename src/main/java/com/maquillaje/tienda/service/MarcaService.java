package com.maquillaje.tienda.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.maquillaje.tienda.model.Marca;

@Service
public class MarcaService {

    // Lista en memoria (no hay base de datos)
    private final List<Marca> marcas = new ArrayList<>();

    public MarcaService() {
        marcas.add(new Marca(1, "DIOR", "Francia",
                "Maison francesa de lujo, referente mundial en moda, perfumería y maquillaje.",
                "/img/marcas/dior.png"));
        marcas.add(new Marca(2, "ATENEA", "Colombia",
                "Marca de maquillaje con tonos pensados para pieles latinas, con productos para rostro, ojos y labios.",
                "/img/marcas/atenea.png"));
        marcas.add(new Marca(3, "MONTOC", "Colombia",
                "Marca de maquillaje y brochas profesionales con sede en Medellín, de alta pigmentación.",
                "/img/marcas/montoc.png"));
    }

    public List<Marca> listarTodas() {
        return marcas;
    }

    public Marca buscarPorId(int id) {
        for (Marca marca : marcas) {
            if (marca.getId() == id) {
                return marca;
            }
        }
        return null;
    }
}
