package org.devbuild.algoservice.exception;

import org.devbuild.algoservice.dto.Error;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@PropertySource("classpath:messages.properties")
public class ExceptionControllerAdvice {


    @Autowired
    private Environment environment;

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Error> genericException(Exception ex){

        var error = new Error();
        error.setMessage(environment.getProperty("generic.exception"));

        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(AlgoServiceException.class)
    public ResponseEntity<Error> genericException(AlgoServiceException ex){

        var error = new Error();
        error.setMessage(environment.getProperty(ex.getMessage()));

        return ResponseEntity.badRequest().body(error);
    }


}
