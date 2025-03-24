package com.first.function_module.service.impl;


import com.first.function_module.entity.ApartmentEntity;
import com.first.function_module.entity.BookingEntity;
import com.first.function_module.entity.UserInfoEntity;
import com.first.function_module.model.dto.RentApartmentDto;
import com.first.function_module.model.dto.geo_dto.Response;
import com.first.function_module.repository.ApartmentRepository;
import com.first.function_module.repository.BookingRepository;
import com.first.function_module.repository.UserRepository;
import com.first.function_module.service.integrated.IntegrationService;
import lombok.RequiredArgsConstructor;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RequiredArgsConstructor
public class ApartmentServiceTest {

    @Mock
    private BookingRepository bookingRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private ApartmentRepository apartmentRepository;

    @Mock
    private IntegrationService integrationService;




    @InjectMocks
    private RentServiceImpl rentService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void rentApartmentSuccess() {

        RentApartmentDto rentApartmentDto = new RentApartmentDto();
        rentApartmentDto.setApartmentId(1L);
        rentApartmentDto.setTimeOfStartRent(LocalDateTime.parse("2025-10-05T11:59:11.332000"));
        rentApartmentDto.setTimeOfRent(7);

        UserInfoEntity userInfoEntity = new UserInfoEntity(1L,
                LocalDateTime.parse("2025-10-05T11:59:11.332000"),
                "cGFzc3dvcmQ="
                , "login1"
                , "greathybi4@gmail.com"
                , "nick1"
                , "83f887bb-eda8-409e-9231-aaaabf36d404|2034-07-10T12:48:11.796983900"
                , "verified");

        ApartmentEntity apartmentEntity = new ApartmentEntity(10.0, 3, "asa", 10000.0);

        when(apartmentRepository.findApartmentById(rentApartmentDto.getApartmentId())).thenReturn(apartmentEntity);
        when(bookingRepository.findBookingEntitiesByApartmentID(rentApartmentDto.getApartmentId())).thenReturn(Collections.emptyList());
        doThrow(new RuntimeException("safe failed")).when(bookingRepository).save(any(BookingEntity.class));
        when(integrationService.getIntegrationWithEmailRejectedBokkingNotification(any(UserInfoEntity.class))).thenReturn(new Response());
        when(integrationService.getIntegrationWithEmailBookingNotification(any(UserInfoEntity.class))).thenReturn(new Response());

        String result = rentService.rentApartment(rentApartmentDto, userInfoEntity);
        Assert.assertNotNull(result);
        verify(bookingRepository).save(any(BookingEntity.class));

//        verify(integrationService).getIntegrationWithEmailBookingNotification(userInfoEntity);

    }

}

