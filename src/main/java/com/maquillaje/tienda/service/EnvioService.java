package com.maquillaje.tienda.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.maquillaje.tienda.model.MetodoEnvio;

@Service
public class EnvioService {

    // Lista en memoria (no hay base de datos)
    private final List<MetodoEnvio> metodos = new ArrayList<>();

    public EnvioService() {
        metodos.add(new MetodoEnvio(1, "Envío estándar", 10000, "3 a 5 días hábiles"));
        metodos.add(new MetodoEnvio(2, "Envío express", 18000, "24 horas"));
    }

    public List<MetodoEnvio> listarTodos() {
        return metodos;
    }

    public MetodoEnvio buscarPorId(int id) {
        for (MetodoEnvio metodo : metodos) {
            if (metodo.getId() == id) {
                return metodo;
            }
        }
        return null;
    }
}
