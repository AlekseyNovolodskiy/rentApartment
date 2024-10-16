package com.first.function_module.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "integration_info")
public class IntegrationInfoEntity {


    @Id
    @SequenceGenerator(name = "integration_infoSequence", sequenceName = "integration_info_sequence", allocationSize = 1, initialValue = 4)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "integration_infoSequence")
    @Column(name = "id", nullable = false)
    private Long id;
    private String name;
    private String url;
    @Column(name="key_info")
    private String key;


}
