package com.sofka.salitremagico.service;

import org.springframework.stereotype.Service;

import com.sofka.salitremagico.model.entity.Atraccion;
import com.sofka.salitremagico.model.values.EstadoAtraccion;
import com.sofka.salitremagico.repository.AtraccionRepository;

@Service
public class AtraccionService {

    private final AtraccionRepository atraccionRepository;

    public AtraccionService(AtraccionRepository atraccionRepository) {
        this.atraccionRepository = atraccionRepository;
    }

    public Atraccion actualizarEstado(Long atraccionId, EstadoAtraccion estadoAtraccion) {
        Atraccion atraccion = atraccionRepository.findById(atraccionId)
            .orElseThrow(() -> new IllegalArgumentException("Atracción no encontrada"));

        atraccion.setEstado(estadoAtraccion);
        return atraccionRepository.save(atraccion);
    }

    public Atraccion actualizarDescripcion(Long atraccionId, String descripcion, String clasificacion) {
        Atraccion atraccion = atraccionRepository.findById(atraccionId)
            .orElseThrow(() -> new IllegalArgumentException("Atracción no encontrada"));

        atraccion.setDescripcion(descripcion);
        atraccion.setClasificacion(clasificacion);
        return atraccionRepository.save(atraccion);
    }
}
