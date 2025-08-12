package com.example.PaymentService.services;

import com.example.PaymentService.Dtos.InitiateRequestDto;
import com.example.PaymentService.PaymentGatewayStrategy.IpaymentGateway;
import com.example.PaymentService.PaymentGatewayStrategy.PaymentGatewayChooserStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    @Autowired
    private PaymentGatewayChooserStrategy paymentGatewayChooserStrategy;


    public String initiatePayment(Long amount,String orderId,String phoneNumber,String name,String email){
        IpaymentGateway ipaymentGateway= paymentGatewayChooserStrategy.getBestPaymentGateway();
        return ipaymentGateway.generatePaymentLink(amount,orderId,phoneNumber,name,email);
    }

}
