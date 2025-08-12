package com.example.PaymentService.Dtos;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class InitiateRequestDto {
    Long amount;
    String orderId;
    String phoneNumber;
    String name;
    String email;
}
