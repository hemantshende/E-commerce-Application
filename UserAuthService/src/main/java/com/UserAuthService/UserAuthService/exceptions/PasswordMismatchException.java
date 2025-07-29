package com.UserAuthService.UserAuthService.exceptions;

public class PasswordMismatchException extends RuntimeException{
    public PasswordMismatchException(String message){
        super(message);
    }
}
