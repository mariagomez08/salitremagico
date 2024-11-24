package com.sofka.salitremagico.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.sofka.salitremagico.model.entity.Atraccion;
import com.sofka.salitremagico.model.entity.Estacion;
import com.sofka.salitremagico.service.TiqueteService;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@Controller
@RequestMapping("/logistica/tiquetes")
@AllArgsConstructor
public class TiqueteController {
    private final TiqueteService tiqueteService;

    @GetMapping("/{id}/registrar")
    public ModelAndView mostrarRegistroTiques(@PathVariable Long id) {
        List<Estacion> estaciones = tiqueteService.listarestacion();
        List<Atraccion> atracciones = tiqueteService.listaratraccion();
        return new ModelAndView("logistica/venta-tiquetes")
                .addObject("atracciones", atracciones)
                .addObject("estaciones", estaciones)
                .addObject("clienteId", id);
    }

    @PostMapping("/{id}/registrar")
    public String registrarTiquete(@PathVariable Long id,
                                   @RequestParam("atraccionId") Long atraccionId,
                                   @RequestParam("estacionId") Long estacionId) {
        // Llamamos al servicio para registrar el tiquete
        tiqueteService.registrarTiquete(id, atraccionId, estacionId);
        return "redirect:/logistica/tiquetes/"+id+"/registrar" ;  // Redirigimos para evitar reenvíos de formulario
    }
    
}
