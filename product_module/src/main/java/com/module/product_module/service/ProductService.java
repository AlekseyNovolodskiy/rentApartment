package com.module.product_module.service;

import com.module.product_module.model.dto.ProductDiscountDto;
import com.module.product_module.model.dto.UserProductIntegrationDto;

public interface ProductService {

    String requestDiscountToFunctionalModul(UserProductIntegrationDto userProductIntegrationDto);
}