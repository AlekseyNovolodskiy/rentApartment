package com.first.function_module.service.impl;

import com.first.function_module.model.EmailDto;
import com.first.function_module.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender javaMailSender;

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
}