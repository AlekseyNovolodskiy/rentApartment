package com.first.function_module.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
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

    @Column(name = "email")
    private String email;

    @Column(name = "nick_name")
    private String nickname;

    @Column(name = "token")
    private String token;

    @Column(name ="verification")
    private String verification;

    @OneToMany(mappedBy = "userInfoEntity", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<BookingEntity> booking;

    @OneToMany(mappedBy = "userInfoEntity", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<UserPublicEntity> users;

    public UserInfoEntity(Long id, LocalDateTime dateRegistration, String password, String login, String email, String nickname, String token, String verification) {
        this.id = id;
        this.dateRegistration = dateRegistration;
        this.password = password;
        this.login = login;
        this.email = email;
        this.nickname = nickname;
        this.token = token;
        this.verification = verification;
    }
}
