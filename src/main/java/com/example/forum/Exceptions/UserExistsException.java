package com.example.forum.Exceptions;

public class UserExistsException extends Exception {
    public UserExistsException(String errorString)
    {
        super(errorString);
    }

}
