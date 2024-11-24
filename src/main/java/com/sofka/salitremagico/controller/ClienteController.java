package com.sofka.salitremagico.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.sofka.salitremagico.model.entity.Cliente;
import com.sofka.salitremagico.model.entity.ContactoFamiliar;
import com.sofka.salitremagico.service.ClienteService;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
@AllArgsConstructor
public class ClienteController {

    private final ClienteService clienteService;

    @GetMapping("/logistica/clientes")
    public ModelAndView listarClientes(@RequestParam(defaultValue = "0") int page, @RequestParam(required = false) String cedula) {
        ModelAndView model = new ModelAndView("/logistica/clientes");
        if (cedula != null && !cedula.isEmpty()) {
            Cliente cliente = clienteService.buscarPorCedula(cedula);
            model.addObject("cliente", cliente);
        } else {
            Page<Cliente> clientes = clienteService.listarClientesPaginados(PageRequest.of(page, 5));
            model.addObject("clientes", clientes);
        }
        model.addObject("cedula", cedula);
        return model;
    }

    @GetMapping("/logistica/clientes/registrar")
    public ModelAndView registrarCliente() {
        return new ModelAndView("/logistica/registrar-cliente")
        .addObject("cliente", new Cliente())
        .addObject("contactoFamiliar", new ContactoFamiliar());
    }

    @PostMapping("/logistica/clientes/registrar")
    public ModelAndView guardarCliente(@Validated  Cliente cliente, @Validated  ContactoFamiliar contactoFamiliar ) {
        clienteService.registrarCliente(cliente, contactoFamiliar);
        return new ModelAndView("redirect:/logistica/clientes");
    }
    
  
    
}
