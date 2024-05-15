package com.fonyou.prueba.service.impl;

import com.fonyou.prueba.dto.EstudianteDto;
import com.fonyou.prueba.entity.Estudiante;
import com.fonyou.prueba.exception.ManageHttpException;
import com.fonyou.prueba.mapper.Mapper;
import com.fonyou.prueba.mapper.impl.ModelMapperImpl;
import com.fonyou.prueba.repository.EstudianteRepository;
import com.fonyou.prueba.service.MessageService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.modelmapper.ModelMapper;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class EstudianteServiceImplTest {

    @InjectMocks
    EstudianteServiceImpl estudianteService;

    @Mock
    EstudianteRepository estudianteRepository;

    @Spy
    Mapper mapper = new ModelMapperImpl(new ModelMapper());

    @Mock
    MessageService messageService;

    Estudiante estudiante;
    EstudianteDto estudianteDto;

    @BeforeEach
    void setUp() throws Exception {
        try (AutoCloseable ignored = MockitoAnnotations.openMocks(this)) {
            estudiante = new Estudiante();
            estudiante.setId(1L);
            estudiante.setNombre("César");
            estudiante.setCiudad("Guayaquil");
            estudiante.setFechaNacimiento(LocalDate.of(1991, 11, 8));
            estudiante.setZonaHoraria("America/Guayaquil");

            estudianteDto = new EstudianteDto();
            estudianteDto.setId(1L);
            estudianteDto.setNombre("César");
            estudianteDto.setCiudad("Guayaquil");
            estudianteDto.setFechaNacimiento(LocalDate.of(1991, 11, 8));
            estudianteDto.setZonaHoraria("America/Guayaquil");
        }
    }

    @Test
    void getEstudiante() {
        when(estudianteRepository.findById(1L)).thenReturn(Optional.of(estudiante));
        assertEquals(estudianteDto, estudianteService.getEstudiante(1L));
        assertThrows(ManageHttpException.class, () -> estudianteService.getEstudiante(2L));
        estudiante.setFechaAnulado(LocalDateTime.now());
        assertThrows(ManageHttpException.class, () -> estudianteService.getEstudiante(1L));
    }

    @Test
    void crearEstudiante() {
        when(estudianteRepository.save(estudiante)).thenReturn(estudiante);
        assertThrows(ManageHttpException.class, () -> estudianteService.crearEstudiante(estudianteDto));
        estudianteDto.setId(null);
        assertEquals(estudianteDto, estudianteService.crearEstudiante(estudianteDto));
    }

    @Test
    void editarEstudiante() {
        when(estudianteRepository.findById(1L)).thenReturn(Optional.of(estudiante));
        when(estudianteRepository.save(estudiante)).thenReturn(estudiante);

        estudianteDto.setNombre("César Augusto");
        assertEquals(estudianteDto, estudianteService.editarEstudiante(1L, estudianteDto));
        estudianteDto.setId(2L);
        assertThrows(ManageHttpException.class, () -> estudianteService.editarEstudiante(1L, estudianteDto));
        estudianteDto.setId(1L);
        estudiante.setFechaAnulado(LocalDateTime.now());
        assertThrows(ManageHttpException.class, () -> estudianteService.editarEstudiante(1L, estudianteDto));
    }

    @Test
    void eliminarEstudiante() {
        when(estudianteRepository.findById(1L)).thenReturn(Optional.of(estudiante));
        when(estudianteRepository.save(estudiante)).thenReturn(estudiante);

        assertThrows(ManageHttpException.class, () -> estudianteService.eliminarEstudiante(2L));
        estudianteService.eliminarEstudiante(1L);
        assertNotNull(estudiante.getFechaAnulado());
        assertThrows(ManageHttpException.class, () -> estudianteService.eliminarEstudiante(1L));

    }
}