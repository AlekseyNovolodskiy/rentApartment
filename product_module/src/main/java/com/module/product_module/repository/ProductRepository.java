package com.module.product_module.repository;

import com.module.product_module.entity.ProductInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductRepository extends JpaRepository<ProductInfoEntity, Long> {

    @Query(value = "select p.discount from ProductInfoEntity p where p.name_discount = :name")
    Double findByName(String name);
}
