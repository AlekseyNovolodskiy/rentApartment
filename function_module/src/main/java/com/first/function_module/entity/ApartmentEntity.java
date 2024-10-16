package com.first.function_module.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity
@Data
@Table(name = "apartment_info")
@NoArgsConstructor
public class ApartmentEntity {

    @Id
    @SequenceGenerator(name = "apartment_infoSequence", sequenceName = "apartment_info_sequence", allocationSize = 1,initialValue = 3)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "apartment_infoSequence")
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "area")
    private Double area;

    @Column(name = "count_of_people")
    private Double countOfPeople;

    @Column(name = "description")
    private String description;

    @Column(name = "cost")
    private Double cost;

    @Column(name = "date_of_registration")
    private LocalDateTime timeOfStartRent;

    @Column(name = "time_of_rent")
    private LocalDateTime timeOfRent;

    @OneToOne (mappedBy = "apartmentEntity")
    private AddressEntity addressEntity;

    @OneToMany(mappedBy="apartmentEntity", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<RateEntity> rates;

    @OneToMany(mappedBy="apartmentEntity", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<PhotoEntity> photos;

    public ApartmentEntity(Double area, Double countOfPeople, String description, Double cost, LocalDateTime timeOfStartRent, LocalDateTime timeOfRent) {
        this.area = area;
        this.countOfPeople = countOfPeople;
        this.description = description;
        this.cost = cost;
        this.timeOfStartRent = timeOfStartRent;
        this.timeOfRent = timeOfRent;
    }
}
