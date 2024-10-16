package com.module.product_module.service.impl;

import com.module.product_module.model.dto.OsmComponents;
import com.module.product_module.model.dto.OsmResults;
import com.module.product_module.service.GeoLocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class GeoLocationServiceImpl implements GeoLocationService {

    @Value("${app.key}")
    private String applicationKey;

    @Value("${app.country}")
    private String appCountry;

    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public OsmComponents geoLocationMethod(String latitude, String longitude) {

        OsmResults response = restTemplate.getForObject("https://api.opencagedata.com/geocode/v1/json?q={query}&key={appkey}&language={appCountry}",
                OsmResults.class,
                latitude + "+" + longitude, applicationKey, appCountry);
        return response.getResults().get(0).getComponents();
    }
}