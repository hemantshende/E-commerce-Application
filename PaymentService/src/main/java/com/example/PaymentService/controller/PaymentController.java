package com.example.PaymentService.controller;


import com.example.PaymentService.Dtos.InitiateRequestDto;
import com.example.PaymentService.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping
    public String initiatePayment(@RequestBody InitiateRequestDto initiateRequestDto){
        return paymentService.initiatePayment(
                initiateRequestDto.getAmount(),
                initiateRequestDto.getOrderId(),
                initiateRequestDto.getPhoneNumber(),
                initiateRequestDto.getName(),
                initiateRequestDto.getEmail());
    }
}
