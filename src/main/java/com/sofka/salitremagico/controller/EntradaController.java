package com.sofka.salitremagico.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sofka.salitremagico.exception.CustomException;
import com.sofka.salitremagico.service.EntradaAtraccionService;

import lombok.AllArgsConstructor;

@Controller
@RequestMapping("/operador/entradas")
@AllArgsConstructor
public class EntradaController {

    private final EntradaAtraccionService entradaService;

    @PostMapping("/{id}/validar")
    public String validarEntrada(@PathVariable Long id, @RequestParam Long atraccionId) {
        try {
            // Validar si el cliente tiene un tiquete válido y si cumple con la estatura
            entradaService.registrarEntrada(id, atraccionId);
            // Si las validaciones pasan, puedes registrar la entrada
            // Aquí puedes realizar el proceso de registro de entrada

            return "redirect:/operador/registrar-clientes"; // Redirigir a una página de éxito
        } catch (CustomException e) {
            // Manejo del error si las validaciones fallan
            return "redirect:/logistica/entradas/error?mensaje=" + e.getMessage(); // Redirigir a una página de error con el mensaje
        }
    }

}