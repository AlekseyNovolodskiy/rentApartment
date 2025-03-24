package com.first.function_module.model.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RentApartmentDto {
    private Long apartmentId;
    private LocalDateTime timeOfStartRent;
    private Integer timeOfRent;


}
