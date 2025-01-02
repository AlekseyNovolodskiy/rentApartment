package com.first.function_module.service.integrated.impl;

import com.first.function_module.entity.IntegrationInfoEntity;
import com.first.function_module.entity.UserInfoEntity;
import com.first.function_module.model.dto.ApartmentDto;
import com.first.function_module.model.dto.geo_dto.Response;
import com.first.function_module.repository.IntegrationRepository;
import com.first.function_module.repository.UserRepository;
import com.first.function_module.service.integrated.IntegrationService;
import com.first.function_module.service.integrated.ModuleUrlService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Locale;

@Service
@RequiredArgsConstructor
public class IntegrationServiceImpl implements IntegrationService {


    private final IntegrationRepository integrationRepository;
    private final RestTemplate restTemplate = new RestTemplate();

    public HttpHeaders prepareHeaderForIntegration(String token) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("token", token);

        return httpHeaders;
    }

    public void getIntegrationWithEmail(UserInfoEntity userByLogin) {
        IntegrationInfoEntity integrationVerification = integrationRepository.findUrlByName("integration_verification");
        restTemplate.exchange(String.format(integrationVerification.getUrl(),userByLogin.getLogin()),

                HttpMethod.GET,
                new HttpEntity<>(null, null),
                String.class);
    }

    @Override
    public Response getIntegrationByGeoLocation(String latitude, String longitude) {



        return restTemplate.exchange(prepareGeoUrl(latitude, longitude),
                HttpMethod.GET,
                new HttpEntity<>(null, null),
                Response.class).getBody();
    }

    private String prepareGeoUrl(String latitude, String longitude) {

        IntegrationInfoEntity geo = integrationRepository.findUrlByName("geo");
        String url = geo.getUrl();
        String key = geo.getKey();

        return String.format(url, latitude, longitude, key);
    }

}

