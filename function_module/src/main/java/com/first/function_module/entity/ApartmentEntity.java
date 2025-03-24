package com.first.function_module.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "apartment_info",
        uniqueConstraints= @UniqueConstraint(columnNames = {"area", "count_of_people","description","cost"})
)
@NoArgsConstructor
public class ApartmentEntity {

    @Id
    @SequenceGenerator(name = "apartment_infoSequence", sequenceName = "apartment_info_sequence", allocationSize = 1, initialValue = 3)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "apartment_infoSequence")
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "area")
    private Double area;

    @Column(name = "count_of_people")
    private Integer countOfPeople;

    @Column(name = "description")
    private String description;

    @Column(name = "cost")
    private Double cost;

    @OneToOne(mappedBy = "apartmentEntity")
    private AddressEntity addressEntity;

    @OneToMany(mappedBy = "apartmentEntity", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<RateEntity> rates;

    @OneToMany(mappedBy = "apartmentEntity", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<PhotoEntity> photos;

    @OneToMany(mappedBy = "apartmentEntity", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<BookingEntity> booking;


    public ApartmentEntity(Double area, Integer countOfPeople, String description, Double cost) {
        this.area = area;
        this.countOfPeople = countOfPeople;
        this.description = description;
        this.cost = cost;
    }
}
