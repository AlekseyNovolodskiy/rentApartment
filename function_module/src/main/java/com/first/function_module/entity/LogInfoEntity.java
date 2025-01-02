package com.first.function_module.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@Table(name="log_info")
public class LogInfoEntity {

    @Id
//    @SequenceGenerator(name = "log_infoSequence", sequenceName = "log_info_sequence", allocationSize = 1)
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "log_infoSequence")
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)

    private Long id;

    private String message;

    private LocalDateTime time_of_logging;


}
