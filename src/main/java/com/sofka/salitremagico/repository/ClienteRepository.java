package com.sofka.salitremagico.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sofka.salitremagico.model.entity.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    Optional<Cliente> findByCedula(String cedula);
}
