package com.first.function_module.service;

import com.first.function_module.entity.UserInfoEntity;
import com.first.function_module.model.dto.ApartmentDto;
import com.first.function_module.model.dto.UserAuthDto;
import com.first.function_module.model.dto.UserInfoDto;

import java.util.List;

public interface RentService {
    public List<ApartmentDto> searchApartment (Double countOfPeople, Double area, Double cost);

    public  String rentApartment(ApartmentDto apartmentDto);

    public  String registerApartment(ApartmentDto apartmentDto, UserInfoEntity userInfoEntity);
    public ApartmentDto showApartment(Long id);
}
