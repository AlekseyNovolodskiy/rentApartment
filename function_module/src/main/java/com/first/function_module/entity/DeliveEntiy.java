package com.first.function_module.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table (name="Delive_info")
public class DeliveEntiy {
    @Id
    @SequenceGenerator(name = "apartment_infoSequence", sequenceName = "apartment_info_sequence", allocationSize = 1,initialValue = 3)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "apartment_infoSequence")
    @Column(name = "id", nullable = false)
    private Long id;

    String fruits;
    double fruits_amount;
    double fruits_price;

    String vegetables;
    double vegetables_amount;
    double vegetables_price;

    String meat;
    double meat_amount;
    double meat_price;

    String juices;
    double juices_amount;
    double juices_price;

}
