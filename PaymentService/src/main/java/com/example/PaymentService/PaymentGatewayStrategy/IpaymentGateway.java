package com.example.PaymentService.PaymentGatewayStrategy;

public interface IpaymentGateway {

    String generatePaymentLink(Long amount,String orderId,String phoneNumber,String name,String email);
}
