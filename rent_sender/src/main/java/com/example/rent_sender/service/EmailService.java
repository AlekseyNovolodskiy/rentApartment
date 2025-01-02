package com.example.rent_sender.service;

public interface EmailService {

    void emailVerification(String sendTo, String subject, String text);
}
