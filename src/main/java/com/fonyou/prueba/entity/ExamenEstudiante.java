package com.fonyou.prueba.entity;

import jakarta.persistence.*;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity(name = "examen_estudiante")
@Setter
@IdClass(ExamenEstudiantePK.class)
public class ExamenEstudiante {
    @Id
    @ManyToOne
    @JoinColumn(name = "id_examen")
    private Examen examen;

    @Id
    @ManyToOne
    @JoinColumn(name = "id_estudiante")
    private Estudiante estudiante;

    @Column
    private LocalDateTime fechaExamen;

    @Column(name = "zona_horaria")
    private String zonaHoraria;

    @Column
    private Integer calificacion;

    @Column(name = "fecha_anulado")
    private LocalDateTime fechaAnulado;
}
