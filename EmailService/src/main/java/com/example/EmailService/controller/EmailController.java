package com.example.EmailService.controller;

import com.example.EmailService.dto.EmailDto;
import com.example.EmailService.util.EmailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/email")
public class EmailController {

    @Autowired
    private EmailUtil emailUtil;

    @PostMapping("/send")
    public String sendEmail(@RequestBody EmailDto emailDto) {
        emailUtil.sendMail(emailDto);
        return "Email sent to: " + emailDto.getTo();
    }
}
