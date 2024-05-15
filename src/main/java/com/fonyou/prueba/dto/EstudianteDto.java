package com.fonyou.prueba.dto;

import lombok.Data;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;

@Data
@Getter
public class EstudianteDto {
    private Long id;
    private String nombre;
    private LocalDate fechaNacimiento;
    private String ciudad;
    private String zonaHoraria;
    private LocalDateTime fechaAnulado;

    public int getEdad() {
        if (fechaNacimiento != null) {
            return Period.between(fechaNacimiento, LocalDate.now()).getYears();
        } else {
            return 0;
        }
    }
}
