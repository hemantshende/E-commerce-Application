package com.UserAuthService.UserAuthService.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class Session extends BaseModel{
    private String token;

    @ManyToOne
    private User user;
    private SessionState state;

}
