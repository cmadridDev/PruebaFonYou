package com.fonyou.prueba.controller;

import com.fonyou.prueba.config.ResponseData;
import com.fonyou.prueba.dto.EstudianteDto;
import com.fonyou.prueba.service.EstudianteService;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/api/estudiantes")
public class EstudianteController {

    private final EstudianteService estudianteService;
    private static final Logger LOGGER = LogManager.getLogger(EstudianteController.class);

    @GetMapping("/{id}")
    public ResponseData<EstudianteDto> getEstudiante(@PathVariable Long id) {
        LOGGER.info("getEstudiante: id={}", id);
        EstudianteDto estudiante = estudianteService.getEstudiante(id);
        return new ResponseData<>(estudiante, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseData<EstudianteDto> crearEstudiante(@RequestBody EstudianteDto estudiante) {
        LOGGER.info("crearEstudiante: estudiante={}", estudiante);
        estudiante = estudianteService.crearEstudiante(estudiante);
        return new ResponseData<>(estudiante, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseData<EstudianteDto> editarEstudiante(@PathVariable Long id, @RequestBody EstudianteDto estudiante) {
        LOGGER.info("editarEstudiante: id={}, estudiante={}", id, estudiante);
        estudiante = estudianteService.editarEstudiante(id, estudiante);
        return new ResponseData<>(estudiante, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseData<String> eliminarEstudiante(@PathVariable Long id) {
        LOGGER.info("eliminarEstudiante: id={}", id);
        estudianteService.eliminarEstudiante(id);
        return new ResponseData<>("Eliminación Correcta", HttpStatus.OK);
    }
}
