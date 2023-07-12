package com.projeto.yonekuraveiculos.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/*
A classe GlobalExceptionHandler serve como um manipulador global de exceções em um aplicativo Spring.
Ela é anotada com @ControllerAdvice para indicar que é responsável por tratar exceções em toda a aplicação.
*/
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
}