package com.first.function_module.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
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
