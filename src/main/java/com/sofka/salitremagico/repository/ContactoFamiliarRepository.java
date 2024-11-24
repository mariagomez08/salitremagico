package com.sofka.salitremagico.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sofka.salitremagico.model.entity.ContactoFamiliar;

public interface ContactoFamiliarRepository  extends JpaRepository<ContactoFamiliar, Long> {
    @Query("SELECT cf FROM ContactoFamiliar cf JOIN Cliente c ON cf.id = c.contactoFamiliar.id WHERE c.id = :clienteId") 
    ContactoFamiliar buscarPorIdCliente(@Param("clienteId") Long clienteId);
}