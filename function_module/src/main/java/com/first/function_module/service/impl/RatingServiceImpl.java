package com.first.function_module.service.impl;

import com.first.function_module.entity.ApartmentEntity;
import com.first.function_module.entity.RateEntity;
import com.first.function_module.exception.ApartmentException;
import com.first.function_module.repository.ApartmentRepository;
import com.first.function_module.repository.RatingRepository;
import com.first.function_module.service.RatingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

@RequiredArgsConstructor
@Service
public class RatingServiceImpl implements RatingService {

    private final ApartmentRepository apartmentRepository;
    private final RatingRepository ratingRepository;
    public static final String ADD_COMMENT = "Коментарий добавлен";
    public static final String RATING_MESSAGE_EXCEPTION = "Апартаментов не обнаружено";


    @Override
    public String addRating(Long apartmentID, Integer rating, String comments) {

        ApartmentEntity apartment = apartmentRepository.findById(apartmentID)
                .orElseThrow(() -> new ApartmentException(RATING_MESSAGE_EXCEPTION,600));

        RateEntity rateEntity = prepareRatingEntity(apartment, rating, comments);
        ratingRepository.save(rateEntity);
        return ADD_COMMENT;
    }

    public RateEntity prepareRatingEntity(ApartmentEntity apartmentEntity, Integer rating, String comments) {

        RateEntity rateEntity = new RateEntity();


        rateEntity.setApartmentEntity(apartmentEntity);
        rateEntity.setRating(rating);
        rateEntity.setComments(comments);

        return rateEntity;
    }
}

