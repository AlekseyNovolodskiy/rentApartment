package com.example.rent_sender.repository;


import com.example.rent_sender.entity.IntegrationInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IntegrationRepository extends JpaRepository<IntegrationInfoEntity,Long> {
    @Query(value = "select i.url from IntegrationInfoEntity i where i.name = :urlName")
    String findUrlByName (String urlName);

}
