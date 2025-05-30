package com.example.mdd.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class UserAlreadyExistException extends Exception{


    private static final long serialVersionUID = 1L;
    public UserAlreadyExistException(String message) {
        super(message);
    }

}