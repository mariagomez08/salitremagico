package com.sofka.salitremagico.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.sofka.salitremagico.model.entity.Estacion;
import com.sofka.salitremagico.model.values.EstadoEstacion;
import com.sofka.salitremagico.service.EstacionService;

@Controller
public class EstacionController {

    private final EstacionService estacionService;

    public EstacionController(EstacionService estacionService) {
        this.estacionService = estacionService;
    }

    @GetMapping("/administrativo/estaciones")
    public ModelAndView listarEstaciones() {
        List<Estacion> estaciones = estacionService.listarEstaciones();
        Long ocupacionParque = estacionService.ocupacionParque(); 
        return new ModelAndView("/administrativo/estaciones")
        .addObject("estaciones", estaciones)
        .addObject("ocupacionParque", ocupacionParque)
        .addObject("estados", EstadoEstacion.values());
    }

    @PostMapping("/administrativo/estaciones/modificar")
    public String modificarEstado(@RequestParam Long id, @RequestParam EstadoEstacion estado) {
        estacionService.modificarEstado(id, estado);
        return "redirect:/administrativo/estaciones";
    }
}