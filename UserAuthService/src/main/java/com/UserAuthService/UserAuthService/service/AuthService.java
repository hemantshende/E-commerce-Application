package com.UserAuthService.UserAuthService.service;

import com.UserAuthService.UserAuthService.exceptions.PasswordMismatchException;
import com.UserAuthService.UserAuthService.exceptions.UserAlreadyExistsException;
import com.UserAuthService.UserAuthService.exceptions.UserNotSignedUpException;
import com.UserAuthService.UserAuthService.models.Session;
import com.UserAuthService.UserAuthService.models.SessionState;
import com.UserAuthService.UserAuthService.models.User;
import com.UserAuthService.UserAuthService.repo.SessionRepo;
import com.UserAuthService.UserAuthService.repo.UserRepo;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import org.antlr.v4.runtime.misc.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class AuthService implements IAuthService{
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private SessionRepo sessionRepo;

    @Autowired
    private SecretKey secretKey;


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
//        user.setPassword(password);
        user.setPassword(bCryptPasswordEncoder.encode(password)); //password security using bcryptPasswordEncoder
        user.setPhoneNumber(phoneNumber);
        return userRepo.save(user);

    }

    @Override
    public Pair<User,String> login(String email, String password) {
        //check user is present or not
        Optional<User> optionalUser=userRepo.findByEmail(email);
        if(optionalUser.isEmpty()){
            throw new UserNotSignedUpException("plz signup first");
        }
        //
        User user=optionalUser.get();

//        if(!user.getPassword().equals(password)){
//            throw  new PasswordMismatchException("plz check ur password again");
//        }

        if(!bCryptPasswordEncoder.matches(password, user.getPassword())){
            throw  new PasswordMismatchException("plz check ur password again");
        }

        Map<String,Object> claims = new HashMap<>();
        claims.put("userId",user.getId());
        claims.put("iss","scaler");
        Long nowInMillis = System.currentTimeMillis();
        claims.put("gen",nowInMillis);
        claims.put("exp",nowInMillis+100000);
        claims.put("scope",user.getRole());

        String token = Jwts.builder().claims(claims).signWith(secretKey).compact();

        Session session = new Session();
        session.setUser(user);
        session.setToken(token);
        session.setState(SessionState.ACTIVE);
        sessionRepo.save(session);

        return new Pair<User,String>(user,token);
    }

    @Override
    public Boolean validateToken(String token, Long userId) {
        Optional<Session> sessionOptional=sessionRepo.findByTokenAndUser_Id(token, userId);
        if(sessionOptional.isEmpty()){
            return false;
        }

        Session session=sessionOptional.get();
        JwtParser jwtParser=Jwts.parser().verifyWith(secretKey).build();
        Claims claims=jwtParser.parseSignedClaims(token).getPayload();

        Long expiry = (Long)claims.get("exp");
        Long currentTime = System.currentTimeMillis();

        System.out.println("token expiry = "+expiry);
        System.out.println("current time ="+currentTime);

        if(currentTime > expiry) {
            session.setState(SessionState.EXPIRED);
            sessionRepo.save(session);
            return false;
        }
        return true;
    }
}
