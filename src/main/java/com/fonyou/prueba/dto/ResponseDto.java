package com.fonyou.prueba.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ResponseDto<T> {

    private final Object value;
    private final String error;
    private final LocalDateTime timestamp = LocalDateTime.now();

    public ResponseDto(T value, String error) {
        this.value = value;
        this.error = error;
    }
}
