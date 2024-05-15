package com.fonyou.prueba.dto;

import lombok.Data;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@Getter
public class ExamenDto {
    private Long id;
    private String nombre;
    private String descripcion;
    private LocalDateTime fechaAnulado;
    private Set<PreguntaDto> preguntas;
}
