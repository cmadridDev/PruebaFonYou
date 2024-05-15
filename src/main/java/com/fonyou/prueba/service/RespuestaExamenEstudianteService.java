package com.fonyou.prueba.service;

import com.fonyou.prueba.dto.CalificacionExamenDto;

import java.util.Map;

public interface RespuestaExamenEstudianteService {
    CalificacionExamenDto crearRespuesta(Long idExamen, Long idEstudiante, Map<Long, Long> respuestasExamen);
}
