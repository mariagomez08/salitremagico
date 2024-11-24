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
import org.springframework.web.servlet.ModelAndView;

import com.sofka.salitremagico.model.entity.Atraccion;
import com.sofka.salitremagico.model.values.EstadoAtraccion;
import com.sofka.salitremagico.service.AtraccionService;

@Controller
public class AtraccionController {

    private final AtraccionService atraccionService;

    public AtraccionController(AtraccionService atraccionService) {
        this.atraccionService = atraccionService;
    }

    @GetMapping("/administrativo/atracciones")
    public ModelAndView listarAtracciones(@PageableDefault(sort = "nombre", size = 5) Pageable pageable) {
        Page<Atraccion> atracciones = atraccionService.listarAtraccion(pageable);
        return new ModelAndView("/administrativo/atracciones")
                .addObject("atracciones", atracciones);
    }

    @GetMapping("/mantenimiento/atracciones")
    public ModelAndView listarAtraccionesMantenimiento(@PageableDefault(sort = "nombre") Pageable pageable) {
        Page<Atraccion> atracciones = atraccionService.listarAtraccion(pageable);
        return new ModelAndView("/mantenimiento/atracciones")
                .addObject("atracciones", atracciones);
    }

    

    @GetMapping("/administrativo/atracciones/{id}/editar")
    public ModelAndView editarEstado(@PathVariable Long id) {
        Atraccion atraccion = atraccionService.buscarAtraccionPorId(id);  
        return new ModelAndView("/administrativo/editar-atraccion")
                .addObject("atraccion", atraccion)
                .addObject("estados", EstadoAtraccion.values()); 
    }

    @PostMapping("/administrativo/atracciones/{id}/editar")
    public String actualizarEstado(@PathVariable Long id,@Validated Atraccion atraccion, BindingResult bindingResult) {
        atraccion.setId(id);
        atraccionService.registrarAtraccion(atraccion);
        return "redirect:/administrativo/atracciones";
    }


}