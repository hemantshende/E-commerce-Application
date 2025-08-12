package com.example.PaymentService.PaymentGatewayStrategy;


import org.springframework.stereotype.Component;

@Component
public class StripePaymentGateway implements IpaymentGateway{
    @Override
    public String generatePaymentLink(Long amount,String orderId,String phoneNumber,String name,String email) {
        return "";
    }
}
