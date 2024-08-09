package com.first.function_module.mapper;

import com.first.function_module.entity.AddressEntity;
import com.first.function_module.entity.ApartmentEntity;
import com.first.function_module.model.dto.ApartmentDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface RentApartmentMapper {

//    @Mapping(target = "countOfPeople", source = "count_of_people")
    public ApartmentEntity apartmentDtoToApartmentEntity (ApartmentDto apartmentDto);

    public AddressEntity addressEntityfromApartmentDto  (ApartmentDto apartmentDto);

   List<ApartmentDto> apartmentEntityToApartmentDto(List<ApartmentEntity> apartmentEntityList);


   ApartmentDto apartmentEntityToApartmentDtowithRating(ApartmentEntity apartmentEntity);

ApartmentEntity dtoToEntity (ApartmentDto apartmentDto);

}
