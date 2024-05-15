package com.fonyou.prueba.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.time.LocalDate;

public class CustomObjectMapper extends ObjectMapper {

    public CustomObjectMapper() {
        // Registra el módulo JavaTimeModule para manejar LocalDate
        registerModule(new JavaTimeModule());

        // Crea un módulo personalizado
        SimpleModule module = new SimpleModule();
        module.addSerializer(LocalDate.class, new ToStringSerializer());
        registerModule(module);

        // Desactiva la escritura de fechas como timestamps
        configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
    }
}