package com.example.rent_sender.controller;

import com.example.rent_sender.model.UserDto;
import com.example.rent_sender.service.EmailSender;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ControllerVerification {

    private final EmailSender emailSender;


    @PostMapping("/verify_auth")
    public void verifyLink(@RequestParam String marker, @RequestBody UserDto userDto) {
        emailSender.emailSender(marker,userDto);

    }
}