package com.sofka.salitremagico.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sofka.salitremagico.model.entity.Estacion;
import com.sofka.salitremagico.model.values.EstadoEstacion;
import com.sofka.salitremagico.repository.EstacionRepository;
import com.sofka.salitremagico.repository.TiqueteRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EstacionService {

    private final EstacionRepository estacionRepository;
    private final TiqueteRepository tiqueteRepository;


    public List<Estacion> listarEstaciones() {
        return estacionRepository.findAll();
    }
    public Long ocupacionParque(){
        return tiqueteRepository.numeroVisitantesHoy();
    }

    public void modificarEstado(Long id, EstadoEstacion estado) {
        Estacion estacion = estacionRepository.findById(id).orElseThrow(() -> 
                new IllegalArgumentException("Estación no encontrada con ID: " + id));
        estacion.setEstado(estado);
        estacionRepository.save(estacion);
    }
}
