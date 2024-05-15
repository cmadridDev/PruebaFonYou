package com.fonyou.prueba.service.impl;

import com.fonyou.prueba.dto.HorarioExamenEstudianteDto;
import com.fonyou.prueba.entity.Estudiante;
import com.fonyou.prueba.entity.Examen;
import com.fonyou.prueba.entity.ExamenEstudiante;
import com.fonyou.prueba.exception.ManageHttpException;
import com.fonyou.prueba.mapper.Mapper;
import com.fonyou.prueba.repository.EstudianteRepository;
import com.fonyou.prueba.repository.ExamenEstudianteRepository;
import com.fonyou.prueba.repository.ExamenRepository;
import com.fonyou.prueba.service.ExamenEstudianteService;
import com.fonyou.prueba.service.MessageService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Set;

@Service
@AllArgsConstructor
public class ExamenEstudianteServiceImpl implements ExamenEstudianteService {

    private final ExamenRepository examenRepository;
    private final EstudianteRepository estudianteRepository;
    private final ExamenEstudianteRepository examenEstudianteRepository;
    private final Mapper mapper;
    private final MessageService messageService;

    @Override
    public Set<HorarioExamenEstudianteDto> asignarExamen(Long idExamen, LocalDateTime fechaExamen, Set<Long> idsEstudiantes) {
        Examen examen = examenRepository.findById(idExamen).orElseThrow(() -> new ManageHttpException(messageService.getMessage(MessageService.ERR_NO_EXAMEN)));
        if(idsEstudiantes==null || idsEstudiantes.isEmpty()) throw new ManageHttpException(messageService.getMessage(MessageService.ERR_NO_ESTUDIANTES));
        if(fechaExamen==null) throw new ManageHttpException(messageService.getMessage(MessageService.ERR_NO_FECHA_EXAMEN));

        ZonedDateTime fechaExamenBogota = fechaExamen.atZone(ZoneId.of("America/Bogota"));

        return idsEstudiantes.stream().map(idEstudiante -> {
            Estudiante estudiante = estudianteRepository.findById(idEstudiante).orElseThrow(() -> new ManageHttpException(messageService.getMessage(MessageService.ERR_NO_ENCONTRADO_ESTUDIANTE)));
            ZonedDateTime fechaExamenEstudiante = fechaExamenBogota.withZoneSameInstant(ZoneId.of(estudiante.getZonaHoraria()));
            HorarioExamenEstudianteDto horarioExamenEstudianteDto = mapper.mapToDto(estudiante, HorarioExamenEstudianteDto.class);
            horarioExamenEstudianteDto.setFechaExamen(fechaExamenEstudiante.toLocalDateTime());

            ExamenEstudiante examenEstudiante = new ExamenEstudiante();
            examenEstudiante.setEstudiante(estudiante);
            examenEstudiante.setExamen(examen);
            examenEstudiante.setFechaExamen(fechaExamenEstudiante.toLocalDateTime());
            examenEstudiante.setZonaHoraria(estudiante.getZonaHoraria());
            examenEstudianteRepository.save(examenEstudiante);

            return horarioExamenEstudianteDto;
        }).collect(java.util.stream.Collectors.toSet());
    }
}
