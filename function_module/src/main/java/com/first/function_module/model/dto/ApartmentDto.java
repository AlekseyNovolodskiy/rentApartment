package com.first.function_module.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ApartmentDto {

    private Double area;
    private Double countOfPeople;
    private String description;
    private LocalDateTime timeOfStartRent;
    private Integer timeOfRent;
    private Double cost;
    private Double avgRate;
    private String city;
    private String street;
    private String buildingNumber;

}
