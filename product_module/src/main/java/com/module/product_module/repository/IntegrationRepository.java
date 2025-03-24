package com.module.product_module.repository;

import com.module.product_module.entity.IntegrationInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface IntegrationRepository extends JpaRepository<IntegrationInfoEntity,Long> {

    @Query(value = "select i from IntegrationInfoEntity i where i.name = :urlName")
    Optional<IntegrationInfoEntity>  findUrlByName (String urlName);

}
