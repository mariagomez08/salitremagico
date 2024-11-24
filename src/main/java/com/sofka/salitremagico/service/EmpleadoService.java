package com.sofka.salitremagico.service;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    public Page<Empleado> listarEmpleados(Pageable pageable) {
        return empleadoRepository.findAll(pageable);
    }

    public Empleado obtenerEmpleadoPorId(Long id) {
        return empleadoRepository.findById(id).orElse(null);
    }

    public Empleado actualizarEmpleado(Empleado empleado) {
        return empleadoRepository.save(empleado);
    }

    public void eliminarEmpleado(Long id) {
        empleadoRepository.deleteById(id);
    }
}
