package com.sofka.salitremagico.service;

import org.springframework.stereotype.Service;

import com.sofka.salitremagico.model.entity.ContactoFamiliar;
import com.sofka.salitremagico.repository.ContactoFamiliarRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ContactoFamiliarService {
    private final ContactoFamiliarRepository contactoFamiliarRepository;

    public ContactoFamiliar registrarContactoFamiliar(ContactoFamiliar contactoFamiliar) {
        return contactoFamiliarRepository.save(contactoFamiliar);
    }

    public ContactoFamiliar buscarPorIdCliente(Long id) {
        return contactoFamiliarRepository.buscarPorIdCliente(id);
    }
    
}
