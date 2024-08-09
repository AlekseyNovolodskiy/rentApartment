package com.first.function_module.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "address_info")
@Data
@NoArgsConstructor
public class AddressEntity {

    @Id
    @SequenceGenerator(name = "address_infoSequence", sequenceName = "address_info_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "address_infoSequence")
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "city")
    private String city;

    @Column(name = "street")
    private String street;

    @Column(name = "building_number")
    private String buildingNumber;

    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name="apartment_id")
    private ApartmentEntity apartmentEntity;

}
