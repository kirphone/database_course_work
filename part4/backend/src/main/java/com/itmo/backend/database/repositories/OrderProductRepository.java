package com.itmo.backend.database.repositories;

import com.itmo.backend.database.entity.OrderProductEntity;
import com.itmo.backend.database.entity.OrderProductKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderProductRepository extends JpaRepository<OrderProductEntity, OrderProductKey> {
}
