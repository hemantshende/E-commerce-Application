package com.UserAuthService.UserAuthService.exceptions;

public class TokenInvalidException extends RuntimeException{
    public TokenInvalidException(String message){
        super(message);
    }
}
