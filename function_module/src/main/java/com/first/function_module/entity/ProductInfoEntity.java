package com.first.function_module.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@RequiredArgsConstructor
@Setter
@Getter
@Table (name = "product_info")
public class ProductInfoEntity {
    @Id
    @SequenceGenerator(name = "product_infoSequence", sequenceName = "product_info_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_infoSequence")
    @Column(name = "id", nullable = false)
    private Long id;

    private Double discount;
    private String name_discount;








}
