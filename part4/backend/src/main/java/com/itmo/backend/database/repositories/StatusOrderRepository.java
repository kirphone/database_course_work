package com.itmo.backend.database.repositories;

import com.itmo.backend.database.entity.StatusOrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StatusOrderRepository extends JpaRepository<StatusOrderEntity, String> {

    Optional<StatusOrderEntity> findByName(String name);
}
