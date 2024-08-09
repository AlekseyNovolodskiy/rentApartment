package com.first.function_module.repository.dao;

import com.first.function_module.entity.ApartmentEntity;

import java.util.List;

public interface ApartmentDao {

    List<ApartmentEntity> findApartmentByCountOfPeople(Double countOfPeople);
}
