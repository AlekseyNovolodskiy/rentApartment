package com.first.function_module.repository.dao;

import com.first.function_module.entity.ApartmentEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ApartmentDaoImpl implements ApartmentDao{


   private final JdbcTemplate jdbcTemplate;
   public static final String APARTMENT_QUERY = "Select * from apartment_info where count_of_people = %s";
    @Override
    public List<ApartmentEntity> findApartmentByCountOfPeople(Double countOfPeople) {

        List<Map<String, Object>> maps = jdbcTemplate.queryForList(String.format(APARTMENT_QUERY, countOfPeople));
        return null;
    }

}
