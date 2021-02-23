package com.itmo.backend.database.repositories;

import com.itmo.backend.database.entity.ShopEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShopRepository extends JpaRepository<ShopEntity, Integer> {


}
