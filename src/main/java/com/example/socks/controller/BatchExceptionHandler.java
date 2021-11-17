package com.example.socks.controller;

import com.example.socks.model.ErrorResponse;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;


import java.util.Map;

@ControllerAdvice
public class BatchExceptionHandler {
    private final Map<Class<? extends Exception>, Integer> exceptionToResponseCode = Map.of(
            ClassNotFoundException.class, 500,
            HttpClientErrorException.BadRequest.class, 400,
            EmptyResultDataAccessException.class, 404
    );
    
    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handle(Exception exception) {
        Integer code = exceptionToResponseCode.get(exception.getClass());
        return ResponseEntity.status(code)
                .body(ErrorResponse.builder()
                        .message(exception.getMessage()).build());
    }
}

