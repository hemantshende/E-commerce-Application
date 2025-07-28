package com.UserAuthService.UserAuthService.service;

import com.UserAuthService.UserAuthService.models.User;
import org.antlr.v4.runtime.misc.Pair;

public class AuthService implements IAuthService{
    @Override
    public User signUp(String name, String email, String password, String phoneNumber) {
        return null;
    }

    @Override
    public Pair<User, String> login(String email, String password) {
        return null;
    }

    @Override
    public Boolean validateToken(String token, Long userId) {
        return null;
    }
}
