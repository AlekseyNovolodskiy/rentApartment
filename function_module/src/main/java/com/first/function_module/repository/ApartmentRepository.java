package com.first.function_module.repository;

import com.first.function_module.entity.ApartmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ApartmentRepository extends JpaRepository<ApartmentEntity, Long> {

   public List<ApartmentEntity> searchApartmentEntitiesByArea (Double area);
   public ApartmentEntity findApartmentEntitiesByArea (Double area);

   public List<ApartmentEntity>  searchApartmentEntitiesByCost(Double cost);


   public List<ApartmentEntity> searchApartmentEntitiesByCostAndAreaAndCountOfPeople(Double cost,Double area, Double numberOfPeople);


   public List<ApartmentEntity> searchApartmentEntitiesByCountOfPeople(Double countOfPeople); //sping data generation

//   @Query (nativeQuery = true,value = "Select * from apartment_info where count_of_people = :countOfPeople")
//   public List<ApartmentEntity> searchApartmentEntitiesWithCountOfPeopleNative(Double countOfPeople); //native query

   @Query(value = "Select a from ApartmentEntity a where a.countOfPeople = :countOfPeople")
   public List<ApartmentEntity> searchApartmentEntitiesWithCountOfPeopleJPQL(Double countOfPeople); //JPQL query

   public List<ApartmentEntity> searchApartmentEntitiesByCountOfPeopleAndArea(Double countOfPeople, Double area);

   public List<ApartmentEntity> searchApartmentEntitiesByCountOfPeopleAndCost(Double countOfPeople, Double cost);

   public List<ApartmentEntity> searchApartmentEntitiesByAreaAndCost(Double area, Double cost);




}
