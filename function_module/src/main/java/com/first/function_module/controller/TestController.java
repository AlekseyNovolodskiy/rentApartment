package com.first.function_module.controller;

import com.first.function_module.model.EmailDto;
import com.first.function_module.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class TestController {

    private final EmailService emailservice;

    @PostMapping("/send_email")
    public String emailSender(@RequestBody EmailDto emailDto) {

        return emailservice.sendEmail(emailDto);
    }


}
