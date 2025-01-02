package com.example.rent_sender.controller;

import com.example.rent_sender.service.EmailSender;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ControllerVerification {

    private final EmailSender emailSender;


    @GetMapping("/verify2")
    public void verifyLink(@RequestParam String marker, @RequestParam String userLogin) {
        emailSender.emailSender(marker,userLogin);

    }
}