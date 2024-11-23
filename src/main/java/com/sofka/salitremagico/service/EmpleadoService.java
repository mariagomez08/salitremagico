package com.sofka.salitremagico.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sofka.salitremagico.model.entity.Empleado;
import com.sofka.salitremagico.repository.EmpleadoRepository;

@Service
public class EmpleadoService {

    private final EmpleadoRepository empleadoRepository;

    public EmpleadoService(EmpleadoRepository empleadoRepository) {
        this.empleadoRepository = empleadoRepository;
    }

    public Empleado registrarEmpleado(Empleado empleado) {
        return empleadoRepository.save(empleado);
    }

    public List<Empleado> listarEmpleados() {
        return empleadoRepository.findAll();
    }
}
