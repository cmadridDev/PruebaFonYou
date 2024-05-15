package com.fonyou.prueba.service;

import com.fonyou.prueba.dto.EstudianteDto;

public interface EstudianteService {

    EstudianteDto getEstudiante(Long id);

    EstudianteDto crearEstudiante(EstudianteDto estudiante);

    EstudianteDto editarEstudiante(Long id, EstudianteDto estudiante);

    void eliminarEstudiante(Long id);
}
