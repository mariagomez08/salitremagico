package com.sofka.salitremagico.model.entity;

import java.util.List;

import com.sofka.salitremagico.model.values.EstadoAtraccion;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "atracciones")
public class Atraccion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String descripcion;

    @Column(nullable = false)
    private String clasificacion;

    @Column(nullable = false)
    private Double estaturaMinima;

    @Column(nullable = false) 
    private Integer visitas = 0;

    @Column(nullable = false)
    private String condiciones;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoAtraccion estado;

    @OneToMany(mappedBy = "atraccion", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Tiquete> tiquetes;
}