package com.UserAuthService.UserAuthService.dto;


import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.autoconfigure.batch.BatchTransactionManager;

@Getter
@Setter
public class LoginRequestDto {
    private String email;
    private String password;

}
