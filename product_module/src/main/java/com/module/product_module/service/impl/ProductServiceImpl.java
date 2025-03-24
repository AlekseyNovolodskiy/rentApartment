package com.module.product_module.service.impl;

import com.module.product_module.model.dto.ProductDiscountDto;
import com.module.product_module.model.dto.UserProductIntegrationDto;
import com.module.product_module.repository.ProductRepository;
import com.module.product_module.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public String requestDiscountToFunctionalModul(UserProductIntegrationDto userProductIntegrationDto) {


        return "discountvalue=" + userDataToMap(userProductIntegrationDto)
                + "|userid=" + userProductIntegrationDto.getUserId()
                + "|apartmentid=" + userProductIntegrationDto.getApartmentId();
    }


    private String userDataToMap(UserProductIntegrationDto userProductIntegrationDto) {
        List<Double> disountList = new ArrayList<>();
        Double bithdayDiscount = productRepository.findByName("bithday_discount");
        Double bussinesDiscount = productRepository.findByName("business_trip");
        Double largeFamilyDiscount = productRepository.findByName("large_family");
        Double onVacationDiscount = productRepository.findByName("vacation_discount");
        Double studentDiscount = productRepository.findByName("student");

        DateTimeFormatter formatterWithTime = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        DateTimeFormatter formatterJustDay = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        LocalDateTime timeOfRegistration = LocalDateTime.parse(userProductIntegrationDto.getTimeOfRegistration(), formatterWithTime);
        LocalDate birthDay = LocalDate.parse(userProductIntegrationDto.getBirthDay(),formatterJustDay);

        int timeOfRent = userProductIntegrationDto.getTimeOfRent();

        if (birthDay.isAfter(ChronoLocalDate.from(timeOfRegistration)) &&
                (birthDay.isBefore(ChronoLocalDate.from(timeOfRegistration.plusDays(timeOfRent))))) {
            disountList.add(bithdayDiscount);
        }

        if (userProductIntegrationDto.getBussines()) {
            disountList.add(bussinesDiscount);
        }
        if (userProductIntegrationDto.getLargeFamily()) {
            disountList.add(largeFamilyDiscount);
        }
        if (userProductIntegrationDto.getOnVacation()) {
            disountList.add(onVacationDiscount);
        }
        if (userProductIntegrationDto.getStudent()) {
            disountList.add(studentDiscount);
        }

        if (disountList.isEmpty()) {
            return "1";
        } else {
            Double value = (1 - disountList.stream().max(Double::compare).get());
            return value.toString();
        }


    }


}
