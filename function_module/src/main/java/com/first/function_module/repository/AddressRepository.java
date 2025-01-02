package com.first.function_module.repository;

import com.first.function_module.entity.AddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface  AddressRepository extends JpaRepository<AddressEntity,Long> {

    @Query(value = "Select a from AddressEntity a where a.street = :street and a.buildingNumber = :buldingNumber")
    AddressEntity searchAddressEntitiesByParam (String street, String buldingNumber);

    @Query(value = "Select a from AddressEntity a where a.city = :city")
    List<AddressEntity> findbyCity(String city);

}
