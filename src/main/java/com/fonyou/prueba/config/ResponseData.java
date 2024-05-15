package com.fonyou.prueba.config;

import com.fonyou.prueba.dto.ResponseDto;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

public class ResponseData<T>  extends ResponseEntity<ResponseDto<T>> {

    public ResponseData(HttpStatusCode status) {
        super(status);
    }

    public ResponseData(T body, HttpStatusCode status) {
        super(new ResponseDto<>(body, null), status);
    }

}
