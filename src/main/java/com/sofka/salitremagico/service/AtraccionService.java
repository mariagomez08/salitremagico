package com.sofka.salitremagico.service;

import java.util.List;
import java.util.stream.Collectors;
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

    public Atraccion registrarAtraccion(Atraccion datosActualizados) {

        Atraccion atraccionExistente = atraccionRepository.findById(datosActualizados.getId())
                .orElseThrow(() -> new RuntimeException("Atracción no encontrada"));

        if (datosActualizados.getPrecio() == null) {
            datosActualizados.setPrecio(atraccionExistente.getPrecio());
        }

        atraccionExistente.setNombre(datosActualizados.getNombre());
        atraccionExistente.setDescripcion(datosActualizados.getDescripcion());
        atraccionExistente.setCondiciones(datosActualizados.getCondiciones());
        atraccionExistente.setClasificacion(datosActualizados.getClasificacion());
        atraccionExistente.setEstaturaMinima(datosActualizados.getEstaturaMinima());
        atraccionExistente.setVisitas(datosActualizados.getVisitas());

        return atraccionRepository.save(atraccionExistente);
    }

    public List<Atraccion> listarAtracciones() {
        return atraccionRepository.findAll();
    }

    public List<Atraccion> listarAtraccionesDisponibles() {
        List<Atraccion> atracciones = atraccionRepository.findAll(); 
        return atracciones.stream() 
                .filter(atraccion -> atraccion.getEstado().equals(EstadoAtraccion.DISPONIBLE))                                                                     
                .collect(Collectors.toList()); 
    }

    public Atraccion buscarAtraccionPorId(Long id) {
        return atraccionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Atracción no encontrada"));
    }

}
