package com.first.function_module.repository;

import com.first.function_module.entity.PhotoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhotoRepository extends JpaRepository<PhotoEntity,Long> {
}
