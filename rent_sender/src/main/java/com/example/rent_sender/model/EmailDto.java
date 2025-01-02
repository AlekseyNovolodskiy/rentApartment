package com.example.rent_sender.model;

import lombok.Data;

@Data
public class EmailDto {
    private  String text;
    private String sendTo;
    private String subject;
}
