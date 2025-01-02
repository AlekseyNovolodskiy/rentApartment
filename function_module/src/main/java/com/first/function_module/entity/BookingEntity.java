package com.first.function_module.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@Table (name = "booking_info")
@NoArgsConstructor
public class BookingEntity {
    @Id
    @SequenceGenerator(name = "booking_infoSequence", sequenceName = "booking_info_sequence", allocationSize = 1, initialValue = 3)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "booking_infoSequence")
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserInfoEntity userInfoEntity;

    private Double bookingDays;

    private Double sum;

}
