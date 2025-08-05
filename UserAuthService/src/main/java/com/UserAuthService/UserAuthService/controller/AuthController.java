package com.UserAuthService.UserAuthService.controller;


import com.UserAuthService.UserAuthService.dto.LoginRequestDto;
import com.UserAuthService.UserAuthService.dto.SignUpRequestDto;
import com.UserAuthService.UserAuthService.dto.UserDto;
import com.UserAuthService.UserAuthService.dto.ValidateTokenRequestDto;
import com.UserAuthService.UserAuthService.exceptions.TokenInvalidException;
import com.UserAuthService.UserAuthService.models.User;
import com.UserAuthService.UserAuthService.service.IAuthService;
import org.antlr.v4.runtime.misc.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
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
        Pair<User,String> response =iAuthService.login(loginRequestDto.getEmail(),loginRequestDto.getPassword());
        User user= response.a;
        String token= response.b;

        MultiValueMap<String,String> header=new LinkedMultiValueMap<>();
        header.add(HttpHeaders.SET_COOKIE,token);
        return  new ResponseEntity<>(from(user),header,HttpStatus.OK);
    }

    //validateToken
    @PostMapping("/validate_token")
    public ResponseEntity<Void> validateToken(@RequestBody ValidateTokenRequestDto validateTokenRequestDto){
        Boolean result = iAuthService.validateToken(validateTokenRequestDto.getToken(),validateTokenRequestDto.getUserId());
        if(!result) {
            throw new TokenInvalidException("Please login again !!");
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    UserDto from(User user){
        UserDto userDto=new UserDto();
        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setEmail(user.getEmail());
        return userDto;
    }
}
