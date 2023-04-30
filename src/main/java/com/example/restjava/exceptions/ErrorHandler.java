package com.example.restjava.exceptions;

import com.example.restjava.annotations.CustomErrorHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.rmi.ServerError;

@RestControllerAdvice(annotations = CustomErrorHandler.class)
public class ErrorHandler {
    private static final Logger logger = LoggerFactory.getLogger(ErrorHandler.class);
    @ExceptionHandler(ServerError.class)
    public ResponseEntity<String> serverError(ServerError error){
        logger.error(error.getMessage(), error);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                new String(error.getMessage()));
    }
}