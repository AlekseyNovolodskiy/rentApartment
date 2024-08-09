package com.first.function_module.service.impl;

import com.first.function_module.entity.AddressEntity;
import com.first.function_module.entity.ApartmentEntity;
import com.first.function_module.entity.RateEntity;
import com.first.function_module.entity.UserInfoEntity;
import com.first.function_module.exception.ApartmentException;
import com.first.function_module.mapper.RentApartmentMapper;
import com.first.function_module.model.dto.ApartmentDto;
import com.first.function_module.repository.AddressRepository;
import com.first.function_module.repository.ApartmentRepository;
import com.first.function_module.repository.RatingRepository;
import com.first.function_module.repository.dao.ApartmentDao;
import com.first.function_module.service.RentService;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


import static com.first.function_module.service.impl.RatingServiceImpl.RATING_MESSAGE_EXCEPTION;
import static java.util.Objects.isNull;

@RequiredArgsConstructor
@Service
public class RentServiceImpl implements RentService {

    private final RatingRepository ratingRepository;

    private final ApartmentRepository apartmentRepository;

    private final RentApartmentMapper mapper;

    private final ApartmentDao apartmentDao;
    private final AddressRepository addressRepository;

    public static final String APARTMENTS_NOT_FOUND = "апартаментов по условию поиска не обнаружено";

    private final EntityManager entityManager;
// критерия билдер
//    private List<ApartmentEntity> findApartmentByCriteria(double countOfPeoplecrit){
//        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
//        CriteriaQuery<ApartmentEntity> query = criteriaBuilder.createQuery(ApartmentEntity.class);
//        Root<ApartmentEntity> root = query.from(ApartmentEntity.class);
//        query.select(root)
//                .where(criteriaBuilder.equal(root.get("countOfPeople"),countOfPeoplecrit));
//        List<ApartmentEntity> resultList = entityManager.createQuery(query).getResultList();
//        return resultList;
//    }

    @Override
    public List<ApartmentDto> searchApartment(Double countOfPeople, Double area, Double cost) {

// поиск по количеству людей
        if (!isNull(countOfPeople) && isNull(area) && isNull(cost)) {
//            List<ApartmentEntity> apartmentEntities = apartmentRepository.searchApartmentEntitiesByCountOfPeople(countOfPeople);
//            List<ApartmentEntity> apartmentEntities = apartmentRepository.searchApartmentEntitiesWithCountOfPeopleNative(countOfPeople);
//            List<ApartmentEntity> apartmentEntities = findApartmentByCriteria(countOfPeople);
            List<ApartmentEntity> apartmentEntities = apartmentRepository.searchApartmentEntitiesWithCountOfPeopleJPQL(countOfPeople);
            apartmentDao.findApartmentByCountOfPeople(countOfPeople);
            if (apartmentEntities.isEmpty()) {
                throw new ApartmentException(APARTMENTS_NOT_FOUND, 600);
            }
            return mapListentityToApartmnetDTO(apartmentEntities);
        }


// поиск по AREA
        if (isNull(countOfPeople) && !isNull(area) && isNull(cost)) {
            List<ApartmentEntity> apartmentEntities = apartmentRepository.searchApartmentEntitiesByArea(area);
//            apartmentEntities.isEmpty()
            if (apartmentEntities.isEmpty()) {
                throw new ApartmentException(APARTMENTS_NOT_FOUND, 600);
            }
            return mapListentityToApartmnetDTO(apartmentEntities);
        }

// поиск по стоимости
        if (isNull(countOfPeople) && isNull(area) && !isNull(cost)) {
            List<ApartmentEntity> apartmentEntities = apartmentRepository.searchApartmentEntitiesByCost(cost);
            if (apartmentEntities.isEmpty()) {
                throw new ApartmentException(APARTMENTS_NOT_FOUND, 600);
            }
            return mapListentityToApartmnetDTO(apartmentEntities);
        }

// поиск по трем показателям
        if (!isNull(countOfPeople) && !isNull(area) && !isNull(cost)) {
            List<ApartmentEntity> apartmentEntities = apartmentRepository.searchApartmentEntitiesByCostAndAreaAndCountOfPeople(cost, area, countOfPeople);
            if (apartmentEntities.isEmpty()) {
                throw new ApartmentException(APARTMENTS_NOT_FOUND, 600);
            }
            return mapListentityToApartmnetDTO(apartmentEntities);
        }

//поиск по площади и стоимости
        if (isNull(countOfPeople) && !isNull(area) && !isNull(cost)) {
            List<ApartmentEntity> apartmentEntities = apartmentRepository.searchApartmentEntitiesByAreaAndCost(area, cost);
            if (apartmentEntities.isEmpty()) {
                throw new ApartmentException(APARTMENTS_NOT_FOUND, 600);
            }
            return mapListentityToApartmnetDTO(apartmentEntities);
        }

//поиск по количеству людей и стоимости
        if (!isNull(countOfPeople) && isNull(area) && !isNull(cost)) {
            List<ApartmentEntity> apartmentEntities = apartmentRepository.searchApartmentEntitiesByCountOfPeopleAndCost(countOfPeople, cost);
            if (apartmentEntities.isEmpty()) {
                throw new ApartmentException(APARTMENTS_NOT_FOUND, 600);
            }
            return mapListentityToApartmnetDTO(apartmentEntities);
        }

// посик поколичеству людей и площади
        if (!isNull(countOfPeople) && !isNull(area) && isNull(cost)) {
            List<ApartmentEntity> apartmentEntities = apartmentRepository.searchApartmentEntitiesByCountOfPeopleAndArea(countOfPeople, area);
            if (apartmentEntities.isEmpty()) {
                throw new ApartmentException(APARTMENTS_NOT_FOUND, 600);
            }
            return mapListentityToApartmnetDTO(apartmentEntities);
        }
        return null;
    }


    private List<ApartmentDto> mapListentityToApartmnetDTO(List<ApartmentEntity> apartmentEntities) {

        return mapper.apartmentEntityToApartmentDto(apartmentEntities);
    }

//    private List<ApartmentDto> mapListentityToApartmnetDTO(List<ApartmentEntity> apartmentEntities) {
//      return   apartmentEntities.stream().map(apartmentEntity -> mapper.apartmentEntityToApartmentDtowithRating(apartmentEntity)).toList();
//    }

    public ApartmentDto showApartment(Long id) {
        ApartmentEntity apartment = apartmentRepository.findById(id)
                .orElseThrow(() -> new ApartmentException(RATING_MESSAGE_EXCEPTION, 600));
        List<RateEntity> rateList = ratingRepository.findRateEntitiesByApartmentEntity(apartment);

        ApartmentDto apartmentDto = mapper.apartmentEntityToApartmentDtowithRating(apartment);

        int temp = 0;
        System.out.println(rateList);
        for (RateEntity r : rateList) {
            temp = temp + r.getRating();
        }
//        for (int i = 0; i < rateList.size(); i++) {
//            temp = (int) (temp + apartmentDto.getAvgRate());
//        }
        double avg = temp / rateList.size();
        apartmentDto.setAvgRate(avg);

        return apartmentDto;
    }


    @Override
    public String rentApartment(ApartmentDto apartmentDto) {

        return null;

    }

    @Override
    public String registerApartment(ApartmentDto apartmentDto, UserInfoEntity userInfoEntity) {
//        ApartmentEntity apartment = apartmentRepository.findApartmentEntitiesByArea(apartmentDto.getArea());
        AddressEntity addressEntityByParam = addressRepository.searchAddressEntitiesByParam(apartmentDto.getStreet(), apartmentDto.getBuildingNumber());

        if (!isNull(addressEntityByParam)) {
            return "апартаменты уже существуют";
        }
        AddressEntity addressEntity = mapper.addressEntityfromApartmentDto(apartmentDto);

        apartmentRepository.save(addressEntity.getApartmentEntity());
        addressRepository.save(addressEntity);

        return userInfoEntity.getNickname()+ " вы зарегистрировали новые апартаменты";

    }


}
