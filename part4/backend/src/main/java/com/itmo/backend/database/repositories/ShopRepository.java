package com.itmo.backend.database.repositories;

import com.itmo.backend.database.entity.ShopEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ShopRepository extends JpaRepository<ShopEntity, Integer> {

    Optional<ShopEntity> findById(Integer id);
    List<ShopEntity> findAll();
}
