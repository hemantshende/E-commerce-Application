package com.example.EmailService.util;

import com.example.EmailService.dto.EmailDto;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class EmailUtil {

    private final JavaMailSender mailSender;

    public EmailUtil(JavaMailSender mailSender){
        this.mailSender=mailSender;
    }

    public void sendMail(EmailDto dto){
        SimpleMailMessage message=new SimpleMailMessage();
        message.setFrom(dto.getFrom());
        message.setTo(dto.getTo());
        message.setSubject(dto.getSubject());
        message.setText(dto.getBody());

        mailSender.send(message);
        System.out.println("mail sent successfully...!!!");
    }



}
