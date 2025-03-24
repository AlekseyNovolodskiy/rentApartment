package com.module.product_module.controller;


import com.module.product_module.model.dto.ProductDiscountDto;
import com.module.product_module.model.dto.UserProductIntegrationDto;
import com.module.product_module.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping ("/responce_discount")
    public String discountResponce (@RequestBody UserProductIntegrationDto userProductIntegrationDto){
       return productService.requestDiscountToFunctionalModul(userProductIntegrationDto);
    }


}

