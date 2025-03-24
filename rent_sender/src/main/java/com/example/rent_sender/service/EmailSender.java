package com.example.rent_sender.service;

import com.example.rent_sender.model.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailSender {

    private final RealizationOfEmailSender realization;

    public void emailSender(String marker, UserDto userDto) {
        if(marker.equals("verify")){
            realization.verifyEmailSender(userDto);
        }
        if(marker.equals("booking_notation")){
            realization.bookingNotation(userDto);
        }
        if(marker.equals("reject")){
            realization.rejectedbookingNotation(userDto);
        }

    }

}
