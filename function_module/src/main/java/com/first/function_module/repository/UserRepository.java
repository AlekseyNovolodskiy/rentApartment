package com.first.function_module.repository;

import com.first.function_module.entity.UserInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserInfoEntity, Long> {

    public UserInfoEntity findUserInfoEntityByNickname (String nickname);
    public UserInfoEntity findUserInfoEntityByLogin (String login);
    public Optional<UserInfoEntity> findUserInfoEntityByToken (String Token);
    public List<UserInfoEntity> findUserInfoEntitiesByTokenIsNotNull ();

}
