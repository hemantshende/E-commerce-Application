package com.UserAuthService.UserAuthService.models;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Role  extends  BaseModel{
    private String value;
}
