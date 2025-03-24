package com.first.function_module.service.impl;

import com.first.function_module.entity.BookingEntity;
import com.first.function_module.model.dto.UserProductIntegrationDto;
import com.first.function_module.repository.BookingRepository;
import com.first.function_module.service.UserDiscountService;
import com.first.function_module.service.integrated.IntegrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserDiscountServiceImpl implements UserDiscountService {

    private final IntegrationService integrationService;
    private final BookingRepository bookingRepository;
    public static final String DISCOUNT_FROM_PRODUCT = "Скидка была расчитана";

    public void discountInjetion(UserProductIntegrationDto userProductIntegrationDto) {

        String discountRequest = integrationService.discountRequest(userProductIntegrationDto);
        Map<String, String> stringToMapMethod = stringToMapMethod(discountRequest);

        BookingEntity byUserAndApartment = bookingRepository.findByUserAndApartment(Long.parseLong(stringToMapMethod.get("userid")),
                Long.parseLong(stringToMapMethod.get("apartmentid")));
        Double sum = byUserAndApartment.getSum();
        byUserAndApartment.setSum(sum * Double.parseDouble(stringToMapMethod.get("discountvalue")));
        bookingRepository.save(byUserAndApartment);

    }

    private Map<String, String> stringToMapMethod(String parse) {
        String[] strArr = parse.split("\\|");
        Map<String, String> valueMap = new HashMap<>();
        for (String str : strArr) {
            valueMap.put(str.substring(0, str.indexOf('=')), (str.substring(str.indexOf('=') + 1)));
        }
        return valueMap;
    }

    public String discountResponce(String discountValue) {

        Map<String, String> stringToMapMethod = stringToMapMethod(discountValue);

        BookingEntity byUserAndApartment = bookingRepository.findByUserAndApartment(Long.parseLong(stringToMapMethod.get("userid")),
                Long.parseLong(stringToMapMethod.get("apartmentid")));
        Double sum = byUserAndApartment.getSum();
        byUserAndApartment.setSum(sum * Double.parseDouble(stringToMapMethod.get("discountvalue")));
        bookingRepository.save(byUserAndApartment);
        return DISCOUNT_FROM_PRODUCT;
    }


}
