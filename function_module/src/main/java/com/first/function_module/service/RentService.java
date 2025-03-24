package com.first.function_module.service;

import com.first.function_module.entity.UserInfoEntity;
import com.first.function_module.model.dto.ApartmentDto;
import com.first.function_module.model.dto.RentApartmentDto;
import com.first.function_module.model.dto.UserAuthDto;
import com.first.function_module.model.dto.UserInfoDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface RentService {

    List<ApartmentDto> searchApartment(Integer countOfPeople, Double area, Double cost);

    String rentApartment(RentApartmentDto rentApartmentDto, UserInfoEntity userInfoEntity);

    String registerApartment(ApartmentDto apartmentDto, UserInfoEntity userInfoEntity);

    ApartmentDto showApartment(Long id);

    List<ApartmentDto> checkByLocation(String latitude, String longitude);

    String addphoto(Long id, MultipartFile multipartFile) throws IOException;

}
