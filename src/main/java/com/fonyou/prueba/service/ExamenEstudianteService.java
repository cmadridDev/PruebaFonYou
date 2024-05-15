package com.fonyou.prueba.service;

import com.fonyou.prueba.dto.HorarioExamenEstudianteDto;

import java.time.LocalDateTime;
import java.util.Set;

public interface ExamenEstudianteService {
    public Set<HorarioExamenEstudianteDto> asignarExamen(Long idExamen, LocalDateTime fechaExamen, Set<Long> idsEstudiantes);
}
