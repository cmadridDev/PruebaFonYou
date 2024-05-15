package com.fonyou.prueba.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;

@Entity(name = "pregunta")
@Getter
@Setter
public class Pregunta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_examen")
    private Examen examen;

    @Column
    private String descripcion;

    @Column
    private Integer puntaje;

    private LocalDateTime fechaAnulado;

    @OneToMany(mappedBy = "pregunta")
    private Set<Opcion> opciones;
}
