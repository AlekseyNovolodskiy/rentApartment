package com.example.rent_sender.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailSender {

    private final RealizationOfEmailSender realization;

    public void emailSender(String marker, String user) {
        if(marker.equals("verify")){
            realization.verifyEmailSender(user);
        }

    }

}
