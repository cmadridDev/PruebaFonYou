package com.fonyou.prueba.exception;

import com.fonyou.prueba.dto.ResponseDto;
import com.fonyou.prueba.service.MessageService;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@AllArgsConstructor
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private final MessageService messageService;
    private static final Logger LOGGER = LogManager.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(ManageHttpException.class)
    public ResponseEntity<ResponseDto<String>> handleManageExceptions(ManageHttpException ex) {
        LOGGER.error(ex.getMessage());
        return new ResponseEntity<>(new ResponseDto<>(ex.getMessage(), messageService.getMessage(MessageService.ERR_ERROR_MANEJADO)), ex.getStatus());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseDto<String>> handleAllExceptions(Exception ex) {
        LOGGER.error(ex.getMessage(), ex);
        return new ResponseEntity<>(new ResponseDto<>("", messageService.getMessage(MessageService.ERR_ERROR_INESPERADO)), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}