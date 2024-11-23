package com.sofka.salitremagico.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sofka.salitremagico.model.entity.EntradaAtraccion;

public interface EntradaAtraccionRepository extends JpaRepository<EntradaAtraccion, Long> {

    @Query("SELECT a.nombre, COUNT(e) FROM EntradaAtraccion e JOIN e.atraccion a GROUP BY a.nombre ORDER BY COUNT(e) DESC")
    List<Object[]> obtenerEstadisticasDeUso();
}