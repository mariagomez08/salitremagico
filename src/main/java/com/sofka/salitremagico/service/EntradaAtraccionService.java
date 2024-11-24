package com.sofka.salitremagico.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.sofka.salitremagico.constants.CustomMessages;
import com.sofka.salitremagico.exception.CustomException;
import com.sofka.salitremagico.model.entity.Atraccion;
import com.sofka.salitremagico.model.entity.Cliente;
import com.sofka.salitremagico.model.entity.EntradaAtraccion;
import com.sofka.salitremagico.model.values.EstadoAtraccion;
import com.sofka.salitremagico.repository.AtraccionRepository;
import com.sofka.salitremagico.repository.ClienteRepository;
import com.sofka.salitremagico.repository.EntradaAtraccionRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EntradaAtraccionService {

    private final EntradaAtraccionRepository entradaAtraccionRepository;
    private final ClienteRepository clienteRepository;
    private final AtraccionRepository atraccionRepository;


    public EntradaAtraccion registrarEntrada(Long clienteId, Long atraccionId) {
        Cliente cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new CustomException(CustomMessages.CLIENTE_NO_ENCONTRADO, HttpStatus.NOT_FOUND));
        Atraccion atraccion = atraccionRepository.findById(atraccionId)
                .orElseThrow(() -> new CustomException(CustomMessages.ATRACCION_NO_ENCONTRADA, HttpStatus.NOT_FOUND));

        if (!atraccion.getEstado().equals(EstadoAtraccion.DISPONIBLE)) {
                    throw new CustomException(CustomMessages.ATRACCION_NO_ENCONTRADA, HttpStatus.BAD_REQUEST);
        }

        if (cliente.getEstatura() < atraccion.getEstaturaMinima()) {
            throw new CustomException( CustomMessages.ESTATURA_INSUFICIENTE, HttpStatus.BAD_REQUEST);
        }

        entradaAtraccionRepository.incrementarVisitas(atraccionId);

        EntradaAtraccion entrada = new EntradaAtraccion();
        entrada.setCliente(cliente);
        entrada.setAtraccion(atraccion);
        entrada.setFechaEntrada(java.time.LocalDateTime.now());
        return entradaAtraccionRepository.save(entrada);
    }

}