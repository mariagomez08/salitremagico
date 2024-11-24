package com.sofka.salitremagico.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sofka.salitremagico.model.entity.EntradaAtraccion;

public interface EntradaAtraccionRepository extends JpaRepository<EntradaAtraccion, Long> {

    @Modifying
    @Query("UPDATE Atraccion a SET a.visitas = a.visitas + 1 WHERE a.id = :atraccionId")
    void incrementarVisitas(@Param("atraccionId") Long atraccionId);

    
}