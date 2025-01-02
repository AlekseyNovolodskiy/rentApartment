package com.example.rent_sender.repository;

import com.example.rent_sender.model.UserInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserInfoEntity,Long> {

    @Query(value = "select a from UserInfoEntity a where a.login = :login")
    UserInfoEntity findUserByEmailJpql (String login);
}
