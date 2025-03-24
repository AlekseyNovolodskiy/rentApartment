package com.module.product_module.model.dto;

import lombok.Data;

@Data
public class DiscountRequestDto {
   private Double discountvalue;
   private Long user_id;
   private Long apartment_id;
}
