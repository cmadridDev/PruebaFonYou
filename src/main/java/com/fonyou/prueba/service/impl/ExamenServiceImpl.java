package com.fonyou.prueba.service.impl;

import com.fonyou.prueba.dto.ExamenDto;
import com.fonyou.prueba.dto.OpcionDto;
import com.fonyou.prueba.dto.PreguntaDto;
import com.fonyou.prueba.entity.Examen;
import com.fonyou.prueba.entity.Opcion;
import com.fonyou.prueba.entity.Pregunta;
import com.fonyou.prueba.exception.ManageHttpException;
import com.fonyou.prueba.mapper.Mapper;
import com.fonyou.prueba.repository.ExamenRepository;
import com.fonyou.prueba.repository.OpcionRepository;
import com.fonyou.prueba.repository.PreguntaRepository;
import com.fonyou.prueba.service.ExamenService;
import com.fonyou.prueba.service.MessageService;
import lombok.AllArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
@AllArgsConstructor
public class ExamenServiceImpl implements ExamenService {

    private final ExamenRepository examenRepository;
    private final PreguntaRepository preguntaRepository;
    private final OpcionRepository opcionRepository;
    private final Mapper mapper;
    private final MessageService messageService;
    private final Environment environment;

    @Override
    @Transactional
    public ExamenDto createExamen(ExamenDto examenDto) {
        validarPreguntas(examenDto.getPreguntas());

        Examen examen = mapper.mapToEntity(examenDto, Examen.class);
        examenRepository.save(examen);

        Set<PreguntaDto> preguntasDto =  crearPreguntas(examenDto.getPreguntas(), examen);
        examenDto = mapper.mapToDto(examen, ExamenDto.class);
        examenDto.setPreguntas(preguntasDto);

        return examenDto;
    }

    private Set<PreguntaDto> crearPreguntas(Set<PreguntaDto> preguntasDto, Examen examen) {
        return preguntasDto.stream().map(preguntaDto -> {

            Pregunta pregunta = mapper.mapToEntity(preguntaDto, Pregunta.class);
            pregunta.setExamen(examen);
            preguntaRepository.save(pregunta);

            Set<OpcionDto> opcionesDto = crearOpciones(preguntaDto.getOpciones(), pregunta);
            preguntaDto = mapper.mapToDto(pregunta, PreguntaDto.class);
            preguntaDto.setOpciones(opcionesDto);
            return preguntaDto;
        }).collect(java.util.stream.Collectors.toSet());
    }

    private Set<OpcionDto> crearOpciones(Set<OpcionDto> opciones, Pregunta pregunta) {
        return opciones.stream().map(opcionDto -> {

            Opcion opcion = mapper.mapToEntity(opcionDto, Opcion.class);
            opcion.setPregunta(pregunta);
            opcionRepository.save(opcion);

            return mapper.mapToDto(opcion, OpcionDto.class);
        }).collect(java.util.stream.Collectors.toSet());
    }

    private void validarPreguntas(Set<PreguntaDto> preguntasDto) {
        if(preguntasDto ==null || preguntasDto.isEmpty()) throw new ManageHttpException(messageService.getMessage(MessageService.ERR_NO_PREGUNTAS));

        Set<String> mensajes = new HashSet<>();
        int sumaPuntajes = Integer.parseInt(environment.getProperty("app.suma.puntajes", "100"));
        int cantidadOpciones = Integer.parseInt(environment.getProperty("app.cantidad.opciones", "4"));

        int puntajeTotal = preguntasDto.stream().mapToInt(PreguntaDto::getPuntaje).sum();
        if(puntajeTotal != sumaPuntajes) mensajes.add(messageService.getMessage(MessageService.ERR_NO_SUMA_PUNTAJE)+sumaPuntajes);

        preguntasDto.forEach(preguntaDto -> {
            if(preguntaDto.getOpciones().size() != cantidadOpciones) mensajes.add(messageService.getMessage(MessageService.ERR_NO_OPCIONES)+cantidadOpciones);
            long correctas = preguntaDto.getOpciones().stream().filter(OpcionDto::getCorrecta).count();
            if(correctas != 1) mensajes.add(messageService.getMessage(MessageService.ERR_OPCIONES_CORRECTAS));
        });

        if(!mensajes.isEmpty()) throw new ManageHttpException(String.join(", ", mensajes));
    }

}
