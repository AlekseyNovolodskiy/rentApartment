package com.first.function_module.service.integrated;

import com.first.function_module.model.dto.ApartmentDto;

import java.util.List;

public interface IntegrationService {

    String integrationWithProduct();

    String testSeconfMethod(String name, String phoneNumber, String token);

    String testThirdMethodWithFilerReader(String name);

    List<ApartmentDto> getIntegrationByGeoLocation(String latitude, String longitude);

}
