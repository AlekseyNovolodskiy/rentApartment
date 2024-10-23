package com.first.function_module.repository;

import com.first.function_module.entity.UserInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserInfoEntity, Long> {

    UserInfoEntity findUserInfoEntityByNickname(String nickname);

    Optional<UserInfoEntity> findUserInfoEntityByToken(String Token);

    List<UserInfoEntity> findUserInfoEntitiesByTokenIsNotNull();

    UserInfoEntity findUserInfoEntitiesByEmail (String email);
@Query(value = "select u from UserInfoEntity u where u.login = :login and u.verification = :verification")
    UserInfoEntity findUserByLoginAndVerificationNumber(String login,String verification);

}
