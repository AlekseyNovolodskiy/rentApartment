package com.first.function_module.repository;

import com.first.function_module.entity.UserInfoEntity;
import com.first.function_module.entity.UserPublicEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserPublicRepository extends JpaRepository<UserPublicEntity,Long> {

    @Query(value = "select u from UserPublicEntity u where  u.userInfoEntity.id= :userId")
    Optional<UserPublicEntity>  findByUser (Long userId);
}
