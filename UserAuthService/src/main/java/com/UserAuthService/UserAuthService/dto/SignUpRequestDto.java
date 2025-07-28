package com.UserAuthService.UserAuthService.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignUpRequestDto {
    private String name;
    private String email;
    private String password;
    private String phoneNumber;
}
