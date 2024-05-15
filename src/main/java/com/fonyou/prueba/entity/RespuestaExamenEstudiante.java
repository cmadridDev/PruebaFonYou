package com.fonyou.prueba.entity;

import jakarta.persistence.*;
import lombok.Setter;

@Entity(name = "respuesta_examen_estudiante")
@Setter
@IdClass(RespuestaExamenEstudiantePK.class)
public class RespuestaExamenEstudiante {
    @Id
    @ManyToOne
    @JoinColumn(name = "id_examen")
    private Examen examen;

    @Id
    @ManyToOne
    @JoinColumn(name = "id_estudiante")
    private Estudiante estudiante;

    @Id
    @ManyToOne
    @JoinColumn(name = "id_pregunta")
    private Pregunta pregunta;

    @Id
    @ManyToOne
    @JoinColumn(name = "id_opcion")
    private Opcion opcion;


}
