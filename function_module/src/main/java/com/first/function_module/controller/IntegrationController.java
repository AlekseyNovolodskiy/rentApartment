package com.first.function_module.controller;

import com.first.function_module.model.dto.ApartmentDto;
import com.first.function_module.service.RentService;
import com.first.function_module.service.integrated.IntegrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/integration")
public class IntegrationController {

    private final RentService rentService;

    private final IntegrationService integrationService;


    @GetMapping("/integration_with_two_parmaters")
    public List<String> integrationWithParameres(@RequestParam String name, @RequestParam String number, @RequestHeader String token) {
        return null;
    }

    @GetMapping("/geolocation")
    public List<ApartmentDto> getInfoByLocation(@RequestParam String latitude, @RequestParam String longitude) {

        return rentService.checkByLocation(latitude, longitude);
    }


}
