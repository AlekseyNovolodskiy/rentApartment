package com.first.function_module.controller;

import com.first.function_module.model.dto.ApartmentDto;
import com.first.function_module.service.RentService;
import com.first.function_module.service.UserDiscountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.first.function_module.controller.ControllerConstants.*;


@RequiredArgsConstructor
@RestController

public class IntegrationController {


    private final RentService rentService;
    private final UserDiscountService discountService;

    @GetMapping(INTEGRATION_BASE_URL + INTEGRATION_GEOLOCATION_URL)
    public List<ApartmentDto> getInfoByLocation(@RequestParam String latitude, @RequestParam String longitude) {

        return rentService.checkByLocation(latitude, longitude);
    }

    @PostMapping(INTEGRATION_BASE_URL+INTEGRATION_DISCOUNT_URL)
    public String getDiscountFromProduct(@RequestBody String discountValue) {
      return   discountService.discountResponce(discountValue);
    }

}
