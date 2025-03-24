package com.module.product_module.model.dto;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class UserProductIntegrationDto {
    private Boolean largeFamily;
    private Boolean bussines;
    private Boolean onVacation;
    private Boolean student;
    private String birthDay;
    private String timeOfRegistration;
    private Integer timeOfRent;
    private Long userId;
    private Long apartmentId;
}
