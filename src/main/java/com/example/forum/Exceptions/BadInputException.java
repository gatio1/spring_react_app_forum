package com.example.forum.Exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(value=HttpStatus.BAD_REQUEST, reason="Not found.")  // 404
public class BadInputException extends RuntimeException{
    public BadInputException(String errorString)
    {
        super(errorString);
    }
}
