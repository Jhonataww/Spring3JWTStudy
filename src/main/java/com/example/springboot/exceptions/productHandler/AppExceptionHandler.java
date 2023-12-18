package com.example.springboot.exceptions.productHandler;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice //serve para escutar nosso controller
public class AppExceptionHandler extends ResponseEntityExceptionHandler { //extendendo e especializando a classe

    @ExceptionHandler (value = {Exception.class}) //qualquer exceção que acontecer vai ser tratada aqui
    public ResponseEntity<Object> handleAnyException(Exception e, WebRequest request) {

        ErrorMensage errorMensage;
        if(e.getLocalizedMessage() == null) errorMensage = new ErrorMensage(new Date(), e.toString());

        errorMensage = new ErrorMensage(new Date(), e.getLocalizedMessage());
        return new ResponseEntity<>(errorMensage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
