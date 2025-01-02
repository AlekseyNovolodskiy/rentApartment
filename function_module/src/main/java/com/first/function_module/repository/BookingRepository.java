package com.first.function_module.repository;

import com.first.function_module.entity.BookingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<BookingEntity,Long> {
}
