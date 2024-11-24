package com.sofka.salitremagico.controller;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sofka.salitremagico.model.entity.Empleado;
import com.sofka.salitremagico.model.values.RolEmpleado;
import com.sofka.salitremagico.service.EmpleadoService;

import lombok.AllArgsConstructor;

import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/administrativo/empleados")
@AllArgsConstructor
public class EmpleadoController {

    private final EmpleadoService empleadoService;

    @GetMapping("")
    public ModelAndView listarEmpleados(@PageableDefault(sort = "nombre", size = 5) Pageable pageable) {
        Page<Empleado> empleados = empleadoService.listarEmpleados(pageable);
        return new ModelAndView("administrativo/empleados").addObject("empleados", empleados);
    }

    @GetMapping("/nuevo")
    public ModelAndView mostrarFormularioDeNuevoEmpleado() {
        return new ModelAndView("administrativo/nuevo-empleado")
                .addObject("empleado", new Empleado())
                .addObject("roles", RolEmpleado.values());
    }

    @PostMapping("/nuevo")
    public ModelAndView registrarEmpleado(@Validated Empleado empleado, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView("administrativo/nuevo-empleado")
                    .addObject("empleado", empleado)
                    .addObject("roles", RolEmpleado.values());
        }
        empleadoService.registrarEmpleado(empleado);
        return new ModelAndView("redirect:/administrativo/empleados");
    }

    @GetMapping("/{id}/editar")
    public ModelAndView mostrarFormularioDeEditarEmpleado(@PathVariable Long id) {
        Empleado empleado = empleadoService.obtenerEmpleadoPorId(id);
        if (empleado == null) {
            return new ModelAndView("redirect:/empleados");
        }
        return new ModelAndView("administrativo/nuevo-empleado")
                .addObject("empleado", empleado)
                .addObject("roles", RolEmpleado.values());
    }

    @PostMapping("/{id}/editar")
    public ModelAndView actualizarEmpleado(@PathVariable Long id, @Validated Empleado empleado, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView("empleados/nuevo-empleado")
                    .addObject("empleado", empleado)
                    .addObject("roles", RolEmpleado.values());
        }
        empleado.setId(id);
        empleadoService.actualizarEmpleado(empleado);
        return new ModelAndView("redirect:/administrativo/empleados");
    }

    @PostMapping("/{id}/eliminar")
    public String eliminarEmpleado(@PathVariable Long id) {
        empleadoService.eliminarEmpleado(id);
        return "redirect:/administrativo/empleados";
    }
    
}
