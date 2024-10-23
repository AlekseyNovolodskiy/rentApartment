package com.first.function_module.service;

import com.first.function_module.model.dto.EmailDto;

public interface EmailService {

    String sendEmail (EmailDto emailDto);
    String emailVerification(String sendTo, String subject, String text);
}
