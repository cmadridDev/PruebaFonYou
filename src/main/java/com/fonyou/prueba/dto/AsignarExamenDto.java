package com.fonyou.prueba.dto;

import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
public class AsignarExamenDto {
    private Set<Long> idsEstudiantes;
    private LocalDateTime fechaExamen;
}
