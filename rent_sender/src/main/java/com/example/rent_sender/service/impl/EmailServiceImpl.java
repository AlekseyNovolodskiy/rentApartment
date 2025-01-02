package com.example.rent_sender.service.impl;

import com.example.rent_sender.service.RealizationOfEmailSender;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@PropertySource("classpath:application.properties")

public class EmailServiceImpl implements com.example.rent_sender.service.EmailService {

    private final JavaMailSender javaMailSender;

    @Value(value = "${spring.mail.username}")
    private String email;


    @Override
    public void emailVerification(String sendTo, String subject, String text) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(email);
        simpleMailMessage.setTo(sendTo);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(text);
        javaMailSender.send(simpleMailMessage);

    }
}