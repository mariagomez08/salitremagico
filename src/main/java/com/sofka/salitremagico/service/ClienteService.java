package com.sofka.salitremagico.service;



import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.sofka.salitremagico.exception.CustomException;
import com.sofka.salitremagico.model.entity.Cliente;
import com.sofka.salitremagico.model.entity.ContactoFamiliar;
import com.sofka.salitremagico.repository.ClienteRepository;

import lombok.AllArgsConstructor;


@Service
@AllArgsConstructor
public class ClienteService {
    
    private final ClienteRepository clienteRepository;
    private final ContactoFamiliarService contactoFamiliarService;

    public Cliente registrarCliente(Cliente cliente, ContactoFamiliar contactoFamiliar) {
        if (cliente.getEdad() < 18 && contactoFamiliar== null) {
            throw new CustomException("", HttpStatus.BAD_REQUEST);
        }

        if (contactoFamiliar != null) {
            // Si el contacto familiar no tiene un id (no está guardado), lo guardamos
            if (contactoFamiliar.getId() == null) {
                contactoFamiliarService.registrarContactoFamiliar(contactoFamiliar);
            }
            // Asociamos el contacto familiar al cliente
            cliente.setContactoFamiliar(contactoFamiliar);
        }
        return clienteRepository.save(cliente);
    }


    public List<Cliente> obtenerClientesFrecuentes(int minVisitas) {
        return clienteRepository.findClientesConVisitas(minVisitas);
    }

    public Page<Cliente> listarClientesPaginados(PageRequest pageRequest) {
        return clienteRepository.findAll(pageRequest);
    }

    public Cliente obtenerClientePorId(Long id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new CustomException("", HttpStatus.NOT_FOUND));
    }

    public Cliente buscarPorCedula(String cedula) {
        return clienteRepository.findByCedula(cedula).orElse(null);
    }

    public void incrementarVisitasCliente(Long clienteId) {
        Cliente cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
        cliente.setVisitas(cliente.getVisitas() + 1);
        clienteRepository.save(cliente);
    }

    public void eliminarCliente(Long id) {
        Cliente cliente = obtenerClientePorId(id);
        clienteRepository.delete(cliente);
    }
}
