package com.first.function_module.repository;

import com.first.function_module.entity.ApartmentEntity;
import com.first.function_module.entity.BookingEntity;
import com.first.function_module.entity.UserInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface BookingRepository extends JpaRepository<BookingEntity,Long> {

    @Query(value = "Select b from BookingEntity b  where b.userInfoEntity = :user and b.apartmentEntity = :apartment" )
    BookingEntity findByUser (UserInfoEntity user, ApartmentEntity apartment);

    @Query(value = "Select b from BookingEntity b where b.userInfoEntity.id = :userId and b.apartmentEntity.id = :apartmentId" )
    BookingEntity findByUserAndApartment (Long userId, Long apartmentId);

    @Query(value = "select b from BookingEntity b where b.timeOfStartRent >= :date_nowMinusDay")
    List<BookingEntity> findByDate(LocalDateTime date_nowMinusDay);

    @Query(value = "select b from BookingEntity b where b.apartmentEntity.id = :apartment")
    List<BookingEntity> findBookingEntitiesByApartmentID(Long apartment);
}

