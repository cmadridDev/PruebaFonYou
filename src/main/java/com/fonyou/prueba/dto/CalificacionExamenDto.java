package com.fonyou.prueba.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class CalificacionExamenDto {
    private Long idExamen;
    private Long idEstudiante;
    private Integer calificacion = 0;
    private Integer correctas = 0;
    private Integer incorrectas = 0;
    private Integer sinResponder = 0;
}
