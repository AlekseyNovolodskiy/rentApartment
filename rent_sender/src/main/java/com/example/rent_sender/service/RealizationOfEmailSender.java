package com.example.rent_sender.service;

import com.example.rent_sender.model.UserDto;

public interface RealizationOfEmailSender {
    void verifyEmailSender(UserDto userDto);
    void bookingNotation(UserDto userDto);
    void rejectedbookingNotation(UserDto userDto);
}
