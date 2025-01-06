package com.example.forum.Exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(value=HttpStatus.FORBIDDEN, reason="Not found.")  // 404
public class ForbiddenException extends RuntimeException{
    public ForbiddenException(String errorString){
        super(errorString);
    }
}
