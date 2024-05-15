package com.fonyou.prueba.service.impl;

import com.fonyou.prueba.dto.CalificacionExamenDto;
import com.fonyou.prueba.entity.*;
import com.fonyou.prueba.exception.ManageHttpException;
import com.fonyou.prueba.repository.*;
import com.fonyou.prueba.service.MessageService;
import com.fonyou.prueba.service.RespuestaExamenEstudianteService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
import java.util.Objects;

@Service
@AllArgsConstructor
public class RespuestaExamenEstudianteServiceImpl implements RespuestaExamenEstudianteService {

    private final ExamenRepository examenRepository;
    private final EstudianteRepository estudianteRepository;
    private final PreguntaRepository preguntaRepository;
    private final OpcionRepository opcionRepository;
    private final RespuestaExamenEstudianteRepository respuestaExamenEstudianteRepository;
    private final ExamenEstudianteRepository examenEstudianteRepository;
    private final MessageService messageService;

    @Override
    @Transactional
    public CalificacionExamenDto crearRespuesta(Long idExamen, Long idEstudiante, Map<Long, Long> respuestasExamen) {
        Examen examen = examenRepository.findById(idExamen).orElseThrow(() -> new ManageHttpException(messageService.getMessage(MessageService.ERR_NO_EXAMEN)));
        Estudiante estudiante = estudianteRepository.findById(idEstudiante).orElseThrow(() -> new ManageHttpException(messageService.getMessage(MessageService.ERR_NO_ESTUDIANTE)));

        ExamenEstudiante examenEstudiante = examenEstudianteRepository.findById(new ExamenEstudiantePK(examen, estudiante)).orElseThrow(() -> new ManageHttpException(messageService.getMessage(MessageService.ERR_NO_EXAMEN_ESTUDIANTE)));

        CalificacionExamenDto calificacionExamenDto = new CalificacionExamenDto();
        calificacionExamenDto.setIdEstudiante(idEstudiante);
        calificacionExamenDto.setIdExamen(idExamen);

        respuestasExamen.forEach((idPregunta, idOpcion) -> calificarExamen(idPregunta, idOpcion, idExamen, estudiante, calificacionExamenDto));

        calificacionExamenDto.setSinResponder(examen.getPreguntas().size() - calificacionExamenDto.getCorrectas() - calificacionExamenDto.getIncorrectas());

        examenEstudiante.setCalificacion(calificacionExamenDto.getCalificacion());
        examenEstudianteRepository.save(examenEstudiante);

        return calificacionExamenDto;
    }

    private void calificarExamen(Long idPregunta, Long idOpcion, Long idExamen, Estudiante estudiante, CalificacionExamenDto calificacionExamenDto){
        Pregunta pregunta = preguntaRepository.findById(idPregunta).orElseThrow(() -> new ManageHttpException(messageService.getMessage(MessageService.ERR_NO_ENCONTRADA_PREGUNTA)));
        if(!Objects.equals(pregunta.getExamen().getId(), idExamen)) throw new ManageHttpException(messageService.getMessage(MessageService.ERR_NO_PREGUNTA_EXAMEN));

        Opcion opcion = opcionRepository.findById(idOpcion).orElseThrow(() -> new ManageHttpException(messageService.getMessage(MessageService.ERR_NO_ENCONTRADA_OPCION)));
        if(!Objects.equals(opcion.getPregunta().getId(), idPregunta)) throw new ManageHttpException(messageService.getMessage(MessageService.ERR_NO_OPCION_PREGUNTA));

        if(respuestaExamenEstudianteRepository.existsByExamenIdAndEstudianteIdAndPreguntaId(idExamen, estudiante.getId(), idPregunta)) throw new ManageHttpException(messageService.getMessage(MessageService.ERR_RESPUESTA_ASIGNADA));

        RespuestaExamenEstudiante respuestaExamenEstudiante = new RespuestaExamenEstudiante();
        respuestaExamenEstudiante.setExamen(pregunta.getExamen());
        respuestaExamenEstudiante.setEstudiante(estudiante);
        respuestaExamenEstudiante.setPregunta(pregunta);
        respuestaExamenEstudiante.setOpcion(opcion);
        respuestaExamenEstudianteRepository.save(respuestaExamenEstudiante);

        if(opcion.getCorrecta()){
            calificacionExamenDto.setCalificacion(calificacionExamenDto.getCalificacion() + pregunta.getPuntaje());
            calificacionExamenDto.setCorrectas(calificacionExamenDto.getCorrectas()+1);
        }
        else
            calificacionExamenDto.setIncorrectas(calificacionExamenDto.getIncorrectas()+1);
    }
}
