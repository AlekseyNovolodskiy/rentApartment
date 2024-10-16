package com.first.function_module.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@Table (name="Delive_info")
public class DeliveEntiy {
    @Id
    @SequenceGenerator(name = "Delive_infoSequence", sequenceName = "Delive_info_sequence", allocationSize = 1,initialValue = 3)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Delive_infoSequence")
    @Column(name = "id", nullable = false)
    private Long id;

    private String fruits;
    private double fruits_amount;
    private double fruits_price;

    private String vegetables;
    private double vegetables_amount;
    private double vegetables_price;

    private String meat;
    private double meat_amount;
    private double meat_price;

    private String juices;
    private double juices_amount;
    private double juices_price;

}
