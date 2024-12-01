package com.example.forum.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.CONFLICT, reason="User exists.")
public class UserExistsException extends RuntimeException {
    public UserExistsException(String errorString)
    {
        super(errorString);
    }

}
