package com.sofka.salitremagico.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.sofka.salitremagico.model.entity.ContactoFamiliar;
import com.sofka.salitremagico.service.ContactoFamiliarService;

import lombok.AllArgsConstructor;

@Controller
@RequestMapping("/logistica/clientes")
@AllArgsConstructor
public class ContactoFamiliarController {
    private final ContactoFamiliarService contactoFamiliarService;

    @GetMapping("/{id}/contacto-familiar")
    public ModelAndView mostrarFormularioDeEditarEmpleado(@PathVariable Long id) {
        ContactoFamiliar contactoFamiliar = contactoFamiliarService.buscarPorIdCliente(id);
        if (contactoFamiliar == null) {
            return new ModelAndView("redirect:/logistica/clientes");
        }
        return new ModelAndView("logistica/contacto-familiar")
        .addObject("contactoFamiliar",contactoFamiliar);
    }
    
    
}
