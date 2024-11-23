package com.sofka.salitremagico.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sofka.salitremagico.model.entity.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {}
