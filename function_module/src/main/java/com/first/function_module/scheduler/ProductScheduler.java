package com.first.function_module.scheduler;

import com.first.function_module.entity.BookingEntity;
import com.first.function_module.repository.BookingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.awt.print.Book;
import java.time.LocalDateTime;
import java.util.List;

@Service
@EnableScheduling
@RequiredArgsConstructor
public class ProductScheduler {

    private final BookingRepository bookingRepository;

    //86400000

    @Scheduled(fixedDelay = 10000)
    private void checkDiscountForUser() {

        List<BookingEntity> byDate = bookingRepository.findByDate(LocalDateTime.now().minusDays(1));


    }
}
