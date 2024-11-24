package com.sofka.salitremagico.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    public Page<Atraccion> listarAtraccion(Pageable pageable) {
        return atraccionRepository.findAll(pageable);
    }

    public Atraccion registrarAtraccion(Atraccion atraccion) {
        return atraccionRepository.save(atraccion);
    }

    public Atraccion buscarAtraccionPorId(Long id) {
        return atraccionRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Atracción no encontrada"));
    }

}
