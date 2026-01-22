package com.sofka.salitremagico.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import com.sofka.salitremagico.exception.CustomException;
import com.sofka.salitremagico.model.entity.Cliente;
import com.sofka.salitremagico.model.entity.ContactoFamiliar;
import com.sofka.salitremagico.repository.ClienteRepository;


@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class ClienteServiceTest {

    @Mock
    private ClienteRepository clienteRepository;

    @Mock
    private ContactoFamiliarService contactoFamiliarService;

    @InjectMocks
    private ClienteService clienteService;



    @Test
    void registrarCliente_MenorDeEdadSinContactoFamiliar_LanzaExcepcion() {
    
        Cliente cliente = new Cliente();
        cliente.setEdad(17);

     
        assertThatThrownBy(() -> clienteService.registrarCliente(cliente, null))
                .isInstanceOf(CustomException.class);
    }

    @Test
    void registrarCliente_ConContactoFamiliarNuevo_GuardaClienteYContacto() {
    
        Cliente cliente = new Cliente();
        cliente.setEdad(17);

        ContactoFamiliar contactoFamiliar = new ContactoFamiliar();

        when(clienteRepository.save(any(Cliente.class))).thenAnswer(i -> {
            Cliente c = i.getArgument(0);
            return c;
        });

     
        Cliente resultado = clienteService.registrarCliente(cliente, contactoFamiliar);


        verify(contactoFamiliarService, times(1)).registrarContactoFamiliar(contactoFamiliar);
        assertThat(resultado).isNotNull();
        assertThat(resultado.getContactoFamiliar()).isEqualTo(contactoFamiliar);
    }

    @Test
    void registrarCliente_ConContactoFamiliarExistente_GuardaCliente() {

        Cliente cliente = new Cliente();
        cliente.setEdad(17);

        ContactoFamiliar contactoFamiliar = new ContactoFamiliar();
        contactoFamiliar.setId(1L);

        when(clienteRepository.save(any(Cliente.class))).thenAnswer(i -> {
            Cliente c = i.getArgument(0);
            return c;
        });

        Cliente resultado = clienteService.registrarCliente(cliente, contactoFamiliar);
        verify(contactoFamiliarService, never()).registrarContactoFamiliar(contactoFamiliar);
        assertThat(resultado).isNotNull();
        assertThat(resultado.getContactoFamiliar()).isEqualTo(contactoFamiliar);
    }

    @Test
    void obtenerClientesFrecuentes_DevuelveListaDeClientes() {

        int minVisitas = 3;
        Cliente cliente1 = new Cliente();
        Cliente cliente2 = new Cliente();
        List<Cliente> clientList = Arrays.asList(cliente1, cliente2);
        when(clienteRepository.findClientesConVisitas(minVisitas)).thenReturn(clientList);
        List<Cliente> resultado = clienteService.obtenerClientesFrecuentes(minVisitas);
        assertThat(resultado).isNotNull().hasSize(2).contains(cliente1, cliente2);
    }

    @Test
    void listarClientesPaginados_DevuelvePaginaDeClientes() {
       
        PageRequest pageRequest = PageRequest.of(0, 10);
        Cliente cliente1 = new Cliente();
        Cliente cliente2 = new Cliente();

        Page<Cliente> page = new PageImpl<>(Arrays.asList(cliente1, cliente2));
        when(clienteRepository.findAll(pageRequest)).thenReturn(page);
        Page<Cliente> resultado = clienteService.listarClientesPaginados(pageRequest);

        assertThat(resultado).isNotNull();
        assertThat(resultado.getContent()).hasSize(2).contains(cliente1, cliente2);
    }

    @Test
    void obtenerClientePorId_ClienteNoExiste_LanzaExcepcion() {
       
        when(clienteRepository.findById(1L)).thenReturn(Optional.empty());

       
        assertThatThrownBy(() -> clienteService.obtenerClientePorId(1L))
                .isInstanceOf(CustomException.class);
    }

    @Test
    void buscarPorCedula_ClienteExiste_DevuelveCliente() {
  
        Cliente cliente = new Cliente();
        when(clienteRepository.findByCedula("123")).thenReturn(Optional.of(cliente));

      
        Cliente resultado = clienteService.buscarPorCedula("123");

       
        assertThat(resultado).isNotNull();
        assertThat(resultado).isEqualTo(cliente);
    }

    @Test
    void buscarPorCedula_ClienteNoExiste_DevuelveNull() {
      
        when(clienteRepository.findByCedula("456")).thenReturn(Optional.empty());

    
        Cliente resultado = clienteService.buscarPorCedula("456");


        assertThat(resultado).isNull();
    }

    @Test
    void incrementarVisitasCliente_IncrementaVisitasYGuardaCliente() {
    
        Cliente cliente = new Cliente();
        cliente.setVisitas(2);
        when(clienteRepository.findById(1L)).thenReturn(Optional.of(cliente));

      
        clienteService.incrementarVisitasCliente(1L);

    
        assertThat(cliente.getVisitas()).isEqualTo(3);
        verify(clienteRepository, times(1)).save(cliente);
    }

    @Test
    void eliminarCliente_ClienteNoExiste_LanzaExcepcion() {
      
        when(clienteRepository.findById(2L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> clienteService.eliminarCliente(2L))
                .isInstanceOf(CustomException.class);
    }

    @Test
    void eliminarCliente_EliminaCliente() {
      
        Cliente cliente = new Cliente();
        when(clienteRepository.findById(1L)).thenReturn(Optional.of(cliente));

       
        clienteService.eliminarCliente(1L);

       
        verify(clienteRepository, times(1)).delete(cliente);
    }
}