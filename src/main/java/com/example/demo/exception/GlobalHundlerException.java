package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalHundlerException {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> internalError(Exception ex){
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body("ERROR Interno: " + ex.getMessage());
    }

    @ExceptionHandler(NotFindIdException.class)
    public ResponseEntity<String> notFindIdException(NotFindIdException ex){
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ex.getMessage());
    }
}
