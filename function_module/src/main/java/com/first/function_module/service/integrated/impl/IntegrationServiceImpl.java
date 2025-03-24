package com.first.function_module.service.integrated.impl;

import com.first.function_module.entity.IntegrationInfoEntity;
import com.first.function_module.entity.UserInfoEntity;
import com.first.function_module.exception.UserException;
import com.first.function_module.model.dto.UserProductIntegrationDto;
import com.first.function_module.model.dto.UserRequestDto;
import com.first.function_module.model.dto.geo_dto.Response;
import com.first.function_module.repository.*;
import com.first.function_module.service.integrated.IntegrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class IntegrationServiceImpl implements IntegrationService {


    private final IntegrationRepository integrationRepository;
    public static final String INTEGRATION_VERIFICATION = "integration_verification";
    public static final String INTEGRATION_BOOKING_NOTATION = "integration_booking";
    public static final String INTEGRATION_REJECTED_BOOKING = "rejectedBooking";
    public static final String USER_NO_EXIST_EXEPTION = "Данного пользователя не существует";

    private final RestTemplate restTemplate = new RestTemplate();
    private final UserRepository userRepository;

    public Response getIntegrationWithEmailVerification(UserInfoEntity userByLogin) {
        IntegrationInfoEntity integrationVerification = integrationRepository.findUrlByName(INTEGRATION_VERIFICATION);

        return restTemplate.exchange(integrationVerification.getUrl(),
                HttpMethod.POST,
                prepareBodyForVerificationMethod(userByLogin.getToken()),
                Response.class).getBody();
    }


    private HttpEntity prepareBodyForVerificationMethod(String token) {
        UserInfoEntity userInfoEntityByToken = userRepository.findUserInfoEntityByToken(token)
                .orElseThrow(() -> new UserException(USER_NO_EXIST_EXEPTION));

        UserRequestDto userRequestDto = new UserRequestDto();
        userRequestDto.setCode(userInfoEntityByToken.getVerification());
        userRequestDto.setLogin(userInfoEntityByToken.getLogin());
        userRequestDto.setEmail(userInfoEntityByToken.getEmail());

        HttpEntity http = new HttpEntity<>(userRequestDto, null);

        return http;
    }

    @Override
    public Response getIntegrationWithEmailBookingNotification(UserInfoEntity userInfoEntity) {
        IntegrationInfoEntity integrationVerification = integrationRepository.findUrlByName(INTEGRATION_BOOKING_NOTATION);
        String url = String.format(integrationVerification.getUrl(), userInfoEntity.getLogin());
        return restTemplate.exchange(url,
                HttpMethod.POST,
                prepareBodyForBookingNotification(userInfoEntity.getToken()),
                Response.class).getBody();
    }

    private HttpEntity prepareBodyForBookingNotification(String token) {
        UserInfoEntity userInfoEntityByToken = userRepository.findUserInfoEntityByToken(token)
                .orElseThrow(() -> new UserException(USER_NO_EXIST_EXEPTION));

        UserRequestDto userRequestDto = new UserRequestDto();
        userRequestDto.setCode(userInfoEntityByToken.getVerification());
        userRequestDto.setLogin(userInfoEntityByToken.getLogin());
        userRequestDto.setEmail(userInfoEntityByToken.getEmail());

        HttpEntity http = new HttpEntity<>(userRequestDto, null);

        return http;
    }

    @Override
    public Response getIntegrationWithEmailRejectedBokkingNotification(UserInfoEntity userInfoEntity) {
        IntegrationInfoEntity integrationVerification = integrationRepository.findUrlByName(INTEGRATION_REJECTED_BOOKING);
        String url = String.format(integrationVerification.getUrl(), userInfoEntity.getLogin());
        return restTemplate.exchange(url,
                HttpMethod.POST,
                prepareBodyForRejectedBokkingNotification(userInfoEntity.getToken()),
                Response.class).getBody();
    }

    private HttpEntity prepareBodyForRejectedBokkingNotification(String token) {
        UserInfoEntity userInfoEntityByToken = userRepository.findUserInfoEntityByToken(token)
                .orElseThrow(() -> new UserException(USER_NO_EXIST_EXEPTION));

        UserRequestDto userRequestDto = new UserRequestDto();
        userRequestDto.setCode(userInfoEntityByToken.getVerification());
        userRequestDto.setLogin(userInfoEntityByToken.getLogin());
        userRequestDto.setEmail(userInfoEntityByToken.getEmail());

        HttpEntity http = new HttpEntity<>(userRequestDto, null);

        return http;
    }

    @Override
    public String discountRequest(UserProductIntegrationDto userProductIntegrationDto) {

        IntegrationInfoEntity discountResponceUrl = integrationRepository.findUrlByName("discountResponce");
        return restTemplate.exchange(discountResponceUrl.getUrl(),
                HttpMethod.POST,
                bodyForproductResponce(userProductIntegrationDto),
                String.class).getBody();
    }



    private HttpEntity bodyForproductResponce(UserProductIntegrationDto userProductIntegrationDto){
        HttpEntity http = new HttpEntity(userProductIntegrationDto,null);
        return http;
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

