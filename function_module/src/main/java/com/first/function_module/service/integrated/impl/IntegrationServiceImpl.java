package com.first.function_module.service.integrated.impl;

import com.first.function_module.model.dto.ApartmentDto;
import com.first.function_module.service.integrated.IntegrationService;
import com.first.function_module.service.integrated.ModuleUrlService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@RequiredArgsConstructor
public class IntegrationServiceImpl implements IntegrationService {

    private final ModuleUrlService moduleUrlService;

    final static String FILEREADER_URL = "http://localhost:9097/filereader";

    @Value("${product.path.url}")
    private String URL;

    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public String integrationWithProduct() {

        String version = restTemplate.exchange(moduleUrlService.productUrlsubstitution("version"),
                HttpMethod.GET,
                new HttpEntity<>(null, null),
                String.class).getBody();
        return version;
    }

    @Override
    public String testSeconfMethod(String name, String phoneNumber, String token) {

        String body = restTemplate.exchange(String.format(moduleUrlService.productUrlsubstitution("nameandphone"), name, phoneNumber),
                HttpMethod.GET,
                new HttpEntity<>(null, prepareHeaderForIntegration(token)),
                String.class).getBody();
        return body;
    }

    public HttpHeaders prepareHeaderForIntegration(String token) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("token", token);

        return httpHeaders;
    }

    @Override
    public String testThirdMethodWithFilerReader(String name) {

        String filereader = "s";
        return filereader;
    }

    @Override
    public List<ApartmentDto> getIntegrationByGeoLocation(String latitude, String longitude) {

        return List.of();
    }


}

