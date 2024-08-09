package com.first.function_module.repository;

import com.first.function_module.entity.ApartmentEntity;
import com.first.function_module.entity.RateEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RatingRepository extends JpaRepository<RateEntity, Long> {
    public List<RateEntity> findRateEntitiesByApartmentEntity(ApartmentEntity apartmentEntity);
}
