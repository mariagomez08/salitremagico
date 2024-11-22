package com.sofka.salitremagico.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.CascadeType;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String cedula;
    private int edad;
    private double estatura;
    private String correo;
    private String telefono;
    @OneToOne(cascade = CascadeType.ALL)
    private ContactoFamiliar contactoFamiliar;
    private boolean menorDeEdad;
    private int visitas;

}
