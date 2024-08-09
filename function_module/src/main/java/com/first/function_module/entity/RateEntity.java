package com.first.function_module.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "rate_apartment")
@NoArgsConstructor
public class RateEntity {
    @Id
    @SequenceGenerator(name = "rate_apartmentSequence", sequenceName = "rate_apartment_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rate_apartmentSequence")
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "rating")
    private Integer rating;

    @Column(name = "Comments", length = 2000)
    private String Comments;

    @ManyToOne
    @JoinColumn(name = "apartment_id")
    private ApartmentEntity apartmentEntity;

}
