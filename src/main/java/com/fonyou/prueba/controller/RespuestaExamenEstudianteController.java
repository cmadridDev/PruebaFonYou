package com.fonyou.prueba.controller;

import com.fonyou.prueba.config.ResponseData;
import com.fonyou.prueba.dto.CalificacionExamenDto;
import com.fonyou.prueba.service.RespuestaExamenEstudianteService;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@AllArgsConstructor
@RestController
@RequestMapping("/api/examenes/respuesta")
public class RespuestaExamenEstudianteController {

    private final RespuestaExamenEstudianteService respuestaExamenEstudianteService;
    private static final Logger LOGGER = LogManager.getLogger(RespuestaExamenEstudianteController.class);

    @PostMapping("/{idExamen}/{idEstudiante}")
    public ResponseData<CalificacionExamenDto> crearRespuesta(@PathVariable Long idExamen, @PathVariable Long idEstudiante, @RequestBody Map<Long, Long> respuestasExamen) {
        LOGGER.info("RespuestaExamenEstudianteController.crearRespuesta idExamen: {} idEstudiante: {} respuestasExamen: {}", idExamen, idEstudiante, respuestasExamen);
        CalificacionExamenDto calificacionExamen = respuestaExamenEstudianteService.crearRespuesta(idExamen, idEstudiante, respuestasExamen);
        return new ResponseData<>(calificacionExamen, HttpStatus.CREATED);
    }
}
