package com.UserAuthService.UserAuthService.exceptions;

public class UserNotSignedUpException extends RuntimeException{
    public UserNotSignedUpException(String message){
        super(message);
    }
}
