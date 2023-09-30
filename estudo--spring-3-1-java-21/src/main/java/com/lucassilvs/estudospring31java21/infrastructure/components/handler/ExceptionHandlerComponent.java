package com.lucassilvs.estudospring31java21.infrastructure.components.handler;

import com.lucassilvs.estudospring31java21.exceptions.UsuarioException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class ExceptionHandlerComponent {


    @ExceptionHandler(UsuarioException.class)
    protected ResponseEntity<ResponseLayerExceptionModel> exceptionResponseEntity(UsuarioException exception){
        return ResponseEntity.status(exception.getStatus()).body(new ResponseLayerExceptionModel(exception.getCamada().getDisplayName(), exception.getMessage()));
    }

}
