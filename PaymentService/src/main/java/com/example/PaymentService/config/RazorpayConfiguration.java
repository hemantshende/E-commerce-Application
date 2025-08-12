package com.example.PaymentService.config;

import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RazorpayConfiguration {

    @Value("${razorpaykey}")
    private String razorpay_keyId;

    @Value("${razorpaySecret}")
    private String razorpay_keySecret;


    @Bean
    public RazorpayClient razorpayClient() throws RazorpayException {
        return new RazorpayClient(razorpay_keyId, razorpay_keySecret);
    }
}
