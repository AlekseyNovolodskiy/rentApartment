package com.first.function_module.repository;

import com.first.function_module.entity.AddressEntity;
import com.first.function_module.entity.ApartmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ApartmentRepository extends JpaRepository<ApartmentEntity, Long> {

    List<ApartmentEntity> searchApartmentEntitiesByArea(Double area);

    ApartmentEntity findApartmentEntitiesByArea(Double area);

    List<ApartmentEntity> searchApartmentEntitiesByCost(Double cost);


    List<ApartmentEntity> searchApartmentEntitiesByCostAndAreaAndCountOfPeople(Double cost, Double area, Double numberOfPeople);

    ApartmentEntity findByCostAndAreaAndCountOfPeople(Double cost, Double area, Double numberOfPeople);

    List<ApartmentEntity> searchApartmentEntitiesByCountOfPeople(Double countOfPeople); //sping data generation

//   @Query (nativeQuery = true,value = "Select * from apartment_info where count_of_people = :countOfPeople")
//   public List<ApartmentEntity> searchApartmentEntitiesWithCountOfPeopleNative(Double countOfPeople); //native query

    @Query(value = "Select a from ApartmentEntity a where a.countOfPeople = :countOfPeople")
    List<ApartmentEntity> searchApartmentEntitiesWithCountOfPeopleJPQL(Double countOfPeople); //JPQL query

    List<ApartmentEntity> searchApartmentEntitiesByCountOfPeopleAndArea(Double countOfPeople, Double area);

    List<ApartmentEntity> searchApartmentEntitiesByCountOfPeopleAndCost(Double countOfPeople, Double cost);

    List<ApartmentEntity> searchApartmentEntitiesByAreaAndCost(Double area, Double cost);

    @Query( value = "select a from ApartmentEntity a join a.addressEntity b where b.city = :city")
    List<ApartmentEntity> findByCity(String city);
}
