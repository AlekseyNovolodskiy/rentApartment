package com.example.rent_sender.repository;

import com.example.rent_sender.model.IntegrationInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IntegrationRepository extends JpaRepository<IntegrationInfoEntity,Long> {

    @Query(value = "select i from IntegrationInfoEntity i where i.name = :name")
    IntegrationInfoEntity findUrlByName(String name);
}
