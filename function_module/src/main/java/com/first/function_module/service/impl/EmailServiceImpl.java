package com.first.function_module.service.impl;

import com.first.function_module.model.dto.EmailDto;
import com.first.function_module.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@PropertySource("classpath:application.properties")
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String email;


    @Override
    public String sendEmail(EmailDto emailDto) {
        SimpleMailMessage simpleMail = new SimpleMailMessage();
        simpleMail.setFrom("novolodsckyalexei@yandex.ru");
        simpleMail.setTo(emailDto.getSendTo());
        simpleMail.setSubject(emailDto.getSubject());
        simpleMail.setText(emailDto.getText());
        javaMailSender.send(simpleMail);
        return "Письмо отправлено";
    }

    @Override
    public String emailVerification(String sendTo, String subject, String text) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(email);
        simpleMailMessage.setTo(sendTo);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(text);
        javaMailSender.send(simpleMailMessage);

        return "";
    }
}