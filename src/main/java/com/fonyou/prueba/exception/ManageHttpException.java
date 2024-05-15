package com.fonyou.prueba.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ManageHttpException extends RuntimeException{
    private HttpStatus status = HttpStatus.BAD_REQUEST;

    public ManageHttpException(String message) {
        super(message);
    }

    public ManageHttpException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }
}
