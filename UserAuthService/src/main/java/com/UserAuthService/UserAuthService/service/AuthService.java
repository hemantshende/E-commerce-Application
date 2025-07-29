package com.UserAuthService.UserAuthService.service;

import com.UserAuthService.UserAuthService.exceptions.PasswordMismatchException;
import com.UserAuthService.UserAuthService.exceptions.UserAlreadyExistsException;
import com.UserAuthService.UserAuthService.exceptions.UserNotSignedUpException;
import com.UserAuthService.UserAuthService.models.User;
import com.UserAuthService.UserAuthService.repo.UserRepo;
import org.antlr.v4.runtime.misc.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService implements IAuthService{
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    @Override
    public User signUp(String name, String email, String password, String phoneNumber) {
        //check user exist or not
        Optional<User> optionalUser =userRepo.findByEmail(email);
        if(optionalUser.isPresent()){
            throw new UserAlreadyExistsException("Please login"); //custom exception
        }

        //save new user in db and return
        User user=new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);
        user.setPhoneNumber(phoneNumber);
        return userRepo.save(user);

    }

    @Override
    public User login(String email, String password) {
        //check user is present or not
        Optional<User> optionalUser=userRepo.findByEmail(email);
        if(optionalUser.isEmpty()){
            throw new UserNotSignedUpException("plz signup first");
        }
        //
        User user=optionalUser.get();

        if(!user.getPassword().equals(password)){
            throw  new PasswordMismatchException("plz check ur password again");
        }

        return user;
    }

    @Override
    public Boolean validateToken(String token, Long userId) {
        return null;
    }
}
