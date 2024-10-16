package com.first.function_module.controller;

import com.first.function_module.service.RentService;
import com.first.function_module.service.integrated.IntegrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class IntegrationController {

    private final IntegrationService integrationService;

    @GetMapping("/product_integration")
    public String integrationWithProduct() {
        return integrationService.integrationWithProduct();
    }

    @GetMapping("/integration_with_two_parmaters")
    public String integrationWithParameres(@RequestParam String name, @RequestParam String number, @RequestHeader String token) {
        return integrationService.testSeconfMethod(name, number, token);
    }
   @GetMapping("/geolocation")
    public String getInfoByLocation(@RequestParam String latitude, @RequestParam String longitude){
        return "";
   }
}
