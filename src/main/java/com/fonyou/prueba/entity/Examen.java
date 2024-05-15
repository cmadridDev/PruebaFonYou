package com.fonyou.prueba.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;

@Entity(name = "examen")
@Getter
@Setter
public class Examen {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String nombre;

    @Column
    private String descripcion;

    @Column(name = "fecha_anulado")
    private LocalDateTime fechaAnulado;

    @OneToMany(mappedBy = "examen")
    private Set<Pregunta> preguntas = Set.of();
}
