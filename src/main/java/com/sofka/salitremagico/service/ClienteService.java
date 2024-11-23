package com.sofka.salitremagico.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.sofka.salitremagico.exception.CustomException;
import com.sofka.salitremagico.model.entity.Cliente;
import com.sofka.salitremagico.repository.ClienteRepository;

import lombok.AllArgsConstructor;


@Service
@AllArgsConstructor
public class ClienteService {
    
    private final ClienteRepository clienteRepository;

    public Cliente registrarCliente(Cliente cliente) {
        if (cliente.getEdad() < 18 && cliente.getContactoFamiliar() == null) {
            throw new CustomException("", HttpStatus.BAD_REQUEST);
        }
        return clienteRepository.save(cliente);
    }

    public List<Cliente> obtenerClientes() {
        return clienteRepository.findAll();
    }

    public Cliente obtenerClientePorId(Long id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new CustomException("", HttpStatus.NOT_FOUND));
    }

    public void eliminarCliente(Long id) {
        Cliente cliente = obtenerClientePorId(id);
        clienteRepository.delete(cliente);
    }
}
