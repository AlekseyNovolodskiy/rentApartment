package com.first.function_module.service.integrated;

import com.first.function_module.entity.UserInfoEntity;
import com.first.function_module.model.dto.ApartmentDto;
import com.first.function_module.model.dto.geo_dto.Response;

import java.util.List;

public interface IntegrationService {

    Response getIntegrationByGeoLocation(String latitude, String longitude);

    void getIntegrationWithEmail(UserInfoEntity userByLogin);
}
