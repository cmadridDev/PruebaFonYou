package com.fonyou.prueba.controller;

import com.fonyou.prueba.config.ResponseData;
import com.fonyou.prueba.dto.ExamenDto;
import com.fonyou.prueba.service.ExamenService;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/api/examenes")
public class ExamenController {

    private final ExamenService examenService;
    private static final Logger LOGGER = LogManager.getLogger(ExamenController.class);

    @PostMapping("/")
    public ResponseData<ExamenDto> crearExamen(@RequestBody ExamenDto examen) {
        LOGGER.info("Crear Examen: {}", examen);
        examen = examenService.createExamen(examen);
        return new ResponseData<>(examen, HttpStatus.CREATED);
    }
}
