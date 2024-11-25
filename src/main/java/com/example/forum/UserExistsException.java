package com.example.forum;

public class UserExistsException extends Exception {
    public UserExistsException(String errorString)
    {
        super(errorString);
    }

}
