package com.fonyou.prueba.controller;

import com.fonyou.prueba.dto.EstudianteDto;
import com.fonyou.prueba.service.EstudianteService;
import com.fonyou.prueba.service.MessageService;
import com.fonyou.prueba.util.CustomObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(EstudianteController.class)
class EstudianteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EstudianteService estudianteService;

    @MockBean
    private MessageService messageService;

    EstudianteDto estudianteDto;
    EstudianteDto newEstudianteDto;

    @BeforeEach
    void setUp() {
        newEstudianteDto = new EstudianteDto();
        newEstudianteDto.setNombre("César");
        newEstudianteDto.setCiudad("Guayaquil");
        newEstudianteDto.setFechaNacimiento(LocalDate.of(1991, 11, 8));
        newEstudianteDto.setZonaHoraria("America/Guayaquil");

        estudianteDto = new EstudianteDto();
        estudianteDto.setId(1L);
        estudianteDto.setNombre("César");
        estudianteDto.setCiudad("Guayaquil");
        estudianteDto.setFechaNacimiento(LocalDate.of(1991, 11, 8));
        estudianteDto.setZonaHoraria("America/Guayaquil");
    }

    @Test
    void getEstudiante() throws Exception {
        when(estudianteService.getEstudiante(1L)).thenReturn(estudianteDto);
        mockMvc.perform(get("/api/estudiantes/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.value.id").value(1))
                .andExpect(jsonPath("$.value.nombre").value("César"))
                .andExpect(jsonPath("$.value.ciudad").value("Guayaquil"))
                .andExpect(jsonPath("$.value.fechaNacimiento").value("1991-11-08"))
                .andExpect(jsonPath("$.value.zonaHoraria").value("America/Guayaquil"));;
    }

    @Test
    void crearEstudiante() throws Exception {
        when(estudianteService.crearEstudiante(newEstudianteDto)).thenReturn(estudianteDto);

        CustomObjectMapper objectMapper = new CustomObjectMapper();
        String estudianteDtoJson = objectMapper.writeValueAsString(newEstudianteDto);

        mockMvc.perform(post("/api/estudiantes/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(estudianteDtoJson))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.value.id").value(1))
                .andExpect(jsonPath("$.value.nombre").value("César"))
                .andExpect(jsonPath("$.value.ciudad").value("Guayaquil"))
                .andExpect(jsonPath("$.value.fechaNacimiento").value("1991-11-08"))
                .andExpect(jsonPath("$.value.zonaHoraria").value("America/Guayaquil"));;
    }

    @Test
    void editarEstudiante() throws Exception  {
        when(estudianteService.editarEstudiante(1L, estudianteDto)).thenReturn(estudianteDto);

        estudianteDto.setNombre("César Augusto");
        CustomObjectMapper objectMapper = new CustomObjectMapper();
        String estudianteDtoJson = objectMapper.writeValueAsString(estudianteDto);

        mockMvc.perform(put("/api/estudiantes/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(estudianteDtoJson))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.value.id").value(1))
                .andExpect(jsonPath("$.value.nombre").value("César Augusto"))
                .andExpect(jsonPath("$.value.ciudad").value("Guayaquil"))
                .andExpect(jsonPath("$.value.fechaNacimiento").value("1991-11-08"))
                .andExpect(jsonPath("$.value.zonaHoraria").value("America/Guayaquil"));;

    }

    @Test
    void eliminarEstudiante() throws Exception {
        mockMvc.perform(delete("/api/estudiantes/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }
}