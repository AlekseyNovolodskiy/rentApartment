package com.first.function_module.service;

import com.first.function_module.model.EmailDto;

public interface EmailService {

    String sendEmail (EmailDto emailDto);
}
