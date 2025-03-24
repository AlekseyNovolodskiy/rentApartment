package com.first.function_module.service.integrated;

import com.first.function_module.entity.UserInfoEntity;
import com.first.function_module.model.dto.UserProductIntegrationDto;
import com.first.function_module.model.dto.geo_dto.Response;

public interface IntegrationService {

    Response getIntegrationByGeoLocation(String latitude, String longitude);

    Response getIntegrationWithEmailVerification(UserInfoEntity userByLogin);

    Response getIntegrationWithEmailBookingNotification(UserInfoEntity userInfoEntity);

    String discountRequest(UserProductIntegrationDto userProductIntegrationDto);

    Response getIntegrationWithEmailRejectedBokkingNotification (UserInfoEntity userInfoEntity);
}
