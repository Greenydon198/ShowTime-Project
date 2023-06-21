package com.project.ShowTime.Exceptions;

public class UserNotFoundException extends Exception{
    public UserNotFoundException(String error){
        super(error);
    }
}
