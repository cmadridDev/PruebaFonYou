package com.fonyou.prueba.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class HorarioExamenEstudianteDto extends EstudianteDto{
    LocalDateTime fechaExamen;
}
