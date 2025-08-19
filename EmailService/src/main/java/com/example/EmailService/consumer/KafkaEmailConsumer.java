package com.example.EmailService.consumer;

import com.example.EmailService.dto.EmailDto;
import com.example.EmailService.util.EmailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;


@Component
public class KafkaEmailConsumer {

    @Autowired
    private EmailUtil emailUtil;

    @KafkaListener(topics = "signup", groupId = "email-service-group")
    public void consume(EmailDto emailDto){
        emailUtil.sendMail(emailDto);
    }
}
