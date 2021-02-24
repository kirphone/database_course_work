package com.itmo.backend.database.repositories;

import com.itmo.backend.database.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<ProductEntity, Integer> {

    Optional<ProductEntity> findById(Integer integer);
}
