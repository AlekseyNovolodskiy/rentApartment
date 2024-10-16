package com.first.function_module.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "photo")
@Data
@NoArgsConstructor
public class PhotoEntity {
    @Id
    @SequenceGenerator(name = "photoSequence", sequenceName = "photo_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "photoSequence")
    @Column(name = "id", nullable = false)
    private Long id;

    private byte[] photo;

    @ManyToOne
    @JoinColumn(name = "apartment_id")
    private ApartmentEntity apartmentEntity;

    public PhotoEntity(byte[] photo, ApartmentEntity apartmentEntity) {
        this.photo = photo;
        this.apartmentEntity = apartmentEntity;
    }
}
