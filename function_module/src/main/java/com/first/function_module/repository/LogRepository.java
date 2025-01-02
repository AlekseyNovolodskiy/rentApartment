package com.first.function_module.repository;

import com.first.function_module.entity.LogInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogRepository extends JpaRepository<LogInfoEntity,Long> {
}
