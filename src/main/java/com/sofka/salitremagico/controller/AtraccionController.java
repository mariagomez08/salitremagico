package com.sofka.salitremagico.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    public ModelAndView listarAtraccionesMantenimiento() {
        List<Atraccion> atracciones = atraccionService.listarAtracciones();
        return new ModelAndView("/mantenimiento/atracciones")
                .addObject("estados", EstadoAtraccion.values())
                .addObject("atracciones", atracciones);
    }

    @GetMapping("/operador/{id}/registrar")
    public ModelAndView mostrarRegistroEntrada(@PathVariable Long id) {
        List<Atraccion> atracciones = atraccionService.listarAtraccionesDisponibles(); 
        return new ModelAndView("/operador/registrar-entrada")
                .addObject("atracciones",atracciones)
                .addObject("id", id);
    }


    @PostMapping("/mantenimiento/atracciones/modificar")
    public String modificarEstado(@RequestParam Long id, @RequestParam EstadoAtraccion estado) {
        atraccionService.actualizarEstado(id, estado);
        return "redirect:/mantenimiento/atracciones";
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