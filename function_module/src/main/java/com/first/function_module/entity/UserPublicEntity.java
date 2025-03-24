package com.first.function_module.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@RequiredArgsConstructor
@Data
@Table(name = "user_public_info")
public class UserPublicEntity {
    @Id
    @SequenceGenerator(name = "User_public_infoSequence", sequenceName = "user_public_info_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "User_public_infoSequence")
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "on_vacation")
    private Boolean onVacation;

    @Column(name = "bussines_trip")
    private Boolean bussinestrip;

    @Column(name = "large_family")
    private Boolean largefamily;


    private Boolean student;

    @Column(name = "birth_day")
    private LocalDate birthday;


    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserInfoEntity userInfoEntity;
}
