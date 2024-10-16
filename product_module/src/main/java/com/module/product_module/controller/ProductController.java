package com.module.product_module.controller;


import com.module.product_module.model.dto.ApartmentDto;
import com.module.product_module.model.dto.OsmComponents;
import com.module.product_module.model.dto.OsmResults;
import com.module.product_module.service.GeoLocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
public class ProductController {
    private final GeoLocationService geoLocationService;


    @GetMapping("/version")
    public String returnVersion() {
        return "version";
    }

    @GetMapping("/testproduct")
    public String testProduct(@RequestParam String name, @RequestParam String phoneNumber, @RequestHeader String token) {
        return "Product module " + name + " for number " + phoneNumber + "token " + token;
    }

    @PostMapping("/filereader")
    public String testFileReader(@RequestBody ApartmentDto apartmentDto) {
        return apartmentDto.toString();
    }
//"https://api.opencagedata.com/geocode/v1/json"

    @GetMapping("/geocode")
    public OsmComponents geoCodByOpenCage(@RequestParam String latitude, @RequestParam String longitude) {
    return geoLocationService.geoLocationMethod(latitude, longitude);
    }
}

