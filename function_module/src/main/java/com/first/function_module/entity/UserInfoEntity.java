package com.first.function_module.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "user_info")
@NoArgsConstructor
public class UserInfoEntity {


    @Id
    @SequenceGenerator(name = "user_infoSequence", sequenceName = "user_info_sequence", initialValue = 3,allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_infoSequence")
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "date_registration")
    private LocalDateTime dateRegistration;

    @Column(name = "password")
    private String password;

    @Column(name = "login")
    private String login;

    @Column(name = "nick_name")
    private String nickname;

    @Column(name = "token")
    private String token;

    public UserInfoEntity(String password, String login, String nickname) {
        this.dateRegistration = LocalDateTime.now();
        this.password = password;
        this.login = login;
        this.nickname = nickname;
    }
}
