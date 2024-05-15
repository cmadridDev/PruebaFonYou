package com.fonyou.prueba.entity;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RespuestaExamenEstudiantePK {
    @ManyToOne
    @JoinColumn(name = "id_examen")
    private Examen examen;

    @ManyToOne
    @JoinColumn(name = "id_estudiante")
    private Estudiante estudiante;

    @ManyToOne
    @JoinColumn(name = "id_pregunta")
    private Pregunta pregunta;

    @ManyToOne
    @JoinColumn(name = "id_opcion")
    private Opcion opcion;
}
