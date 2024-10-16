package com.first.function_module.repository;

import com.first.function_module.entity.IntegrationInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ModuleUrlRepository extends JpaRepository<IntegrationInfoEntity,Long> {
   IntegrationInfoEntity findByName(String name);
}
