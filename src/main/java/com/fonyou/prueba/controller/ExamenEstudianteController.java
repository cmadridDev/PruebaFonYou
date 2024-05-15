package com.fonyou.prueba.controller;

import com.fonyou.prueba.config.ResponseData;
import com.fonyou.prueba.dto.AsignarExamenDto;
import com.fonyou.prueba.dto.HorarioExamenEstudianteDto;
import com.fonyou.prueba.service.ExamenEstudianteService;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@AllArgsConstructor
@RestController
@RequestMapping("/api/examenes/estudiantes")
public class ExamenEstudianteController {

    private final ExamenEstudianteService examenEstudianteService ;
    private static final Logger LOGGER = LogManager.getLogger(ExamenEstudianteController.class);

    @PostMapping("/{idExamen}")
    public ResponseData<Set<HorarioExamenEstudianteDto>> crearExamenEstudiante(@PathVariable Long idExamen, @RequestBody AsignarExamenDto asignarExamenDto) {
        LOGGER.info("ExamenEstudianteController.crearExamenEstudiante idExamen: {} asignarExamenDto: {}", idExamen, asignarExamenDto);
        Set<HorarioExamenEstudianteDto> horarioExamenEstudianteDto = examenEstudianteService.asignarExamen(idExamen, asignarExamenDto.getFechaExamen(), asignarExamenDto.getIdsEstudiantes());
        return new ResponseData<>(horarioExamenEstudianteDto, HttpStatus.CREATED);
    }
}
