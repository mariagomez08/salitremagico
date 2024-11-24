package com.sofka.salitremagico.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sofka.salitremagico.model.entity.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    Optional<Cliente> findByCedula(String cedula);

    @Query("SELECT c FROM Cliente c WHERE c.visitas > :minVisitas")
    List<Cliente> findClientesConVisitas(@Param("minVisitas") int minVisitas);
}
