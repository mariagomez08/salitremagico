package com.sofka.salitremagico.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sofka.salitremagico.model.entity.Tiquete;

public interface TiqueteRepository extends JpaRepository<Tiquete, Long> {

    @Query("SELECT COUNT(DISTINCT t.cliente.id) FROM Tiquete t WHERE t.fechaVenta >= CURRENT_DATE")
    Long numeroVisitantesHoy();
}
