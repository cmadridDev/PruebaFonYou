package com.fonyou.prueba.dto;

import lombok.Data;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@Getter
public class PreguntaDto {
    private Long id;
    private String descripcion;
    private Integer puntaje;
    private LocalDateTime fechaAnulado;
    private Set<OpcionDto> opciones;
}
