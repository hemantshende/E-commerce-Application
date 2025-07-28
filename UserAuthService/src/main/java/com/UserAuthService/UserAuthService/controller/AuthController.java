package com.UserAuthService.UserAuthService.controller;


import com.UserAuthService.UserAuthService.dto.LoginRequestDto;
import com.UserAuthService.UserAuthService.dto.SignUpRequestDto;
import com.UserAuthService.UserAuthService.dto.UserDto;
import com.UserAuthService.UserAuthService.dto.ValidateTokenRequestDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    //signup
    @PostMapping("/signup")
    public ResponseEntity<UserDto> signup(@RequestBody SignUpRequestDto signUpRequestDto){
        return null;
    }


    //login
    @PostMapping("/login")
    public ResponseEntity<UserDto> login(@RequestBody LoginRequestDto loginRequestDto){
        return  null;
    }

    //validateToken
    @PostMapping("/validate_token")
    public ResponseEntity<Void> validateToken(@RequestBody ValidateTokenRequestDto validateTokenRequestDto){
        return null;
    }
}
