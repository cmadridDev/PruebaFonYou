package com.fonyou.prueba.dto;

import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class OpcionDto {
    private Long id;
    private String descripcion;
    private Boolean correcta = false;
}
