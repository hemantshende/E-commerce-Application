package com.UserAuthService.UserAuthService.controller;


import com.UserAuthService.UserAuthService.dto.LoginRequestDto;
import com.UserAuthService.UserAuthService.dto.SignUpRequestDto;
import com.UserAuthService.UserAuthService.dto.UserDto;
import com.UserAuthService.UserAuthService.dto.ValidateTokenRequestDto;
import com.UserAuthService.UserAuthService.models.User;
import com.UserAuthService.UserAuthService.service.IAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private IAuthService iAuthService;

    //signup
    @PostMapping("/signup")
    public ResponseEntity<UserDto> signup(@RequestBody SignUpRequestDto signUpRequestDto){
        User user=iAuthService.signUp(
                signUpRequestDto.getName(),
                signUpRequestDto.getEmail(),
                signUpRequestDto.getPassword(),
                signUpRequestDto.getPhoneNumber());

        UserDto userDto=from(user);
        return new ResponseEntity<>(userDto, HttpStatus.CREATED);
    }


    //login
    @PostMapping("/login")
    public ResponseEntity<UserDto> login(@RequestBody LoginRequestDto loginRequestDto){
        User user=iAuthService.login(loginRequestDto.getEmail(),loginRequestDto.getPassword());
        UserDto userDto=from(user);
        return  new ResponseEntity<>(userDto,HttpStatus.ACCEPTED);
    }

    //validateToken
    @PostMapping("/validate_token")
    public ResponseEntity<Void> validateToken(@RequestBody ValidateTokenRequestDto validateTokenRequestDto){
        return null;
    }

    UserDto from(User user){
        UserDto userDto=new UserDto();
        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setEmail(user.getEmail());
        return userDto;
    }
}
