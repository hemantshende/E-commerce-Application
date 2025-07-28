package com.UserAuthService.UserAuthService.service;

import com.UserAuthService.UserAuthService.models.User;
import org.antlr.v4.runtime.misc.Pair;

public interface IAuthService {

    public User signUp(String name, String email, String password, String phoneNumber);

    Pair<User,String> login(String email, String password);

    Boolean validateToken(String token, Long userId);
}
