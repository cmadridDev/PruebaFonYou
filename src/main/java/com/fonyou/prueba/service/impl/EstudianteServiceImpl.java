package com.fonyou.prueba.service.impl;

import com.fonyou.prueba.entity.Estudiante;
import com.fonyou.prueba.exception.ManageHttpException;
import com.fonyou.prueba.mapper.Mapper;
import com.fonyou.prueba.dto.EstudianteDto;
import com.fonyou.prueba.repository.EstudianteRepository;
import com.fonyou.prueba.service.EstudianteService;
import com.fonyou.prueba.service.MessageService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Service
@AllArgsConstructor
public class EstudianteServiceImpl implements EstudianteService {

    private final EstudianteRepository estudianteRepository;
    private final Mapper mapper;
    private final MessageService messageService;

    public EstudianteDto getEstudiante(Long id) {
        Estudiante estudiante = estudianteRepository.findById(id).orElseThrow(() -> new ManageHttpException(messageService.getMessage(MessageService.ERR_OBJ_NO_ENCONTRADO)));
        if(estudiante.getFechaAnulado() != null) throw new ManageHttpException(messageService.getMessage(MessageService.ERR_OBJ_INACTIVO));
        return mapper.mapToDto(estudiante, EstudianteDto.class);
    }

    public EstudianteDto crearEstudiante(EstudianteDto estudianteDto) {
        if(estudianteDto.getId() != null) throw new ManageHttpException(messageService.getMessage(MessageService.ERR_NO_DEFINIR_ID));
        if(!esZonaHorariaValida(estudianteDto.getZonaHoraria())) throw new ManageHttpException(messageService.getMessage(MessageService.ERR_ZONA_NO_VALIDA) );

        Estudiante estudiante = mapper.mapToEntity(estudianteDto, Estudiante.class);
        estudianteRepository.save(estudiante);
        return mapper.mapToDto(estudiante, EstudianteDto.class);
    }

    public EstudianteDto editarEstudiante(Long id, EstudianteDto estudianteDto) {
        if(!esZonaHorariaValida(estudianteDto.getZonaHoraria())) throw new ManageHttpException(messageService.getMessage(MessageService.ERR_ZONA_NO_VALIDA) );
        if( !id.equals(estudianteDto.getId()) ) throw new ManageHttpException(messageService.getMessage(MessageService.ERR_NO_COINCIDE_ID));
        Estudiante estudiante = estudianteRepository.findById(id).orElseThrow(() -> new ManageHttpException(messageService.getMessage(MessageService.ERR_OBJ_NO_ENCONTRADO)));
        if(estudiante.getFechaAnulado() != null) throw new ManageHttpException(messageService.getMessage(MessageService.ERR_OBJ_INACTIVO));

        estudiante = mapper.mapToEntity(estudianteDto, Estudiante.class);
        estudianteRepository.save(estudiante);
        return mapper.mapToDto(estudiante, EstudianteDto.class);
    }

    public void eliminarEstudiante(Long id) {
        Estudiante estudiante = estudianteRepository.findById(id).orElseThrow(() -> new ManageHttpException(messageService.getMessage(MessageService.ERR_OBJ_NO_ENCONTRADO)));
        if(estudiante.getFechaAnulado() != null) throw new ManageHttpException(messageService.getMessage(MessageService.ERR_OBJ_INACTIVO));

        estudiante.setFechaAnulado(LocalDateTime.now());
        estudianteRepository.save(estudiante);
    }

    private boolean esZonaHorariaValida(String zonaHoraria) {
        try {
            ZoneId.of(zonaHoraria);
            return true;
        } catch (DateTimeException e) {
            return false;
        }
    }
}
