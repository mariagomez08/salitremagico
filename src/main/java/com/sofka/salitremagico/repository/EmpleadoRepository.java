package com.sofka.salitremagico.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sofka.salitremagico.model.entity.Empleado;

public interface EmpleadoRepository extends JpaRepository<Empleado, Long> {}
