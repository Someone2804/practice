package com.study.exceptions.exeptionhandler;


import com.study.exceptions.ServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class DefaultAdvice {


    @ExceptionHandler(ServiceException.class)
    public ResponseEntity<Response> exceptionHandler(ServiceException e){
        return new ResponseEntity<>(new Response(e.getMessage()), HttpStatus.BAD_REQUEST);
    }
}
