package com.first.function_module.service;

import com.first.function_module.entity.ApartmentEntity;
import com.first.function_module.entity.UserInfoEntity;
import com.first.function_module.model.dto.UserProductIntegrationDto;

public interface UserDiscountService {
     void discountInjetion (UserProductIntegrationDto userProductIntegrationDto);
     String discountResponce(String discountValue);

}
