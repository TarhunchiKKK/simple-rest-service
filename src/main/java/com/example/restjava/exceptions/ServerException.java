package com.example.restjava.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//класс ошибки сервера
@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Some problems with server")
public class ServerException extends RuntimeException{
    private static final long serialVersionUID = 1L;
    public ServerException(String message){
        super(message);
    }
}

