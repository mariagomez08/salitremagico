package com.sofka.salitremagico.service;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.sofka.salitremagico.exception.CustomException;
import com.sofka.salitremagico.model.entity.Atraccion;
import com.sofka.salitremagico.model.entity.Cliente;
import com.sofka.salitremagico.model.entity.Estacion;
import com.sofka.salitremagico.model.entity.Tiquete;
import com.sofka.salitremagico.model.values.EstadoAtraccion;
import com.sofka.salitremagico.model.values.EstadoEstacion;
import com.sofka.salitremagico.repository.AtraccionRepository;
import com.sofka.salitremagico.repository.ClienteRepository;
import com.sofka.salitremagico.repository.EstacionRepository;
import com.sofka.salitremagico.repository.TiqueteRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TiqueteService {

    private final TiqueteRepository tiqueteRepository;
    private final AtraccionRepository atraccionRepository;
    private final EstacionRepository estacionRepository;
    private final ClienteRepository clienteRepository;

    public Tiquete registrarTiquete(Long clienteId, Long atraccionId, Long estacionId, Double precio) {
        Cliente cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new CustomException("Cliente no encontrado", HttpStatus.NOT_FOUND));
        Atraccion atraccion = atraccionRepository.findById(atraccionId)
                .orElseThrow(() -> new CustomException( "Atracción no encontrada.",HttpStatus.NOT_FOUND));
        Estacion estacion = estacionRepository.findById(estacionId)
                .orElseThrow(() -> new CustomException( "Estación no encontrada.",HttpStatus.NOT_FOUND));

        if (!atraccion.getEstado().equals(EstadoAtraccion.DISPONIBLE)) {
            throw new CustomException( "La atracción no está disponible.",HttpStatus.BAD_REQUEST);
        }

        if (!estacion.getEstado().equals(EstadoEstacion.HABILITADA)) {
            throw new CustomException( "La estación no está habilitada.", HttpStatus.BAD_REQUEST);
        }

        Tiquete tiquete = new Tiquete();
        tiquete.setCliente(cliente);
        tiquete.setAtraccion(atraccion);
        tiquete.setEstacion(estacion);
        tiquete.setFechaVenta(LocalDateTime.now());
        tiquete.setPrecio(precio);

        return tiqueteRepository.save(tiquete);
    }
}
