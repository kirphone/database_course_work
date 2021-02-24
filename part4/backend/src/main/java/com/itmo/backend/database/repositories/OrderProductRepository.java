package com.itmo.backend.database.repositories;

import com.itmo.backend.database.entity.OrderEntity;
import com.itmo.backend.database.entity.OrderProductEntity;
import com.itmo.backend.database.entity.OrderProductKey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderProductRepository extends JpaRepository<OrderProductEntity, OrderProductKey> {

    List<OrderProductEntity> findByOrder(OrderEntity order);
}
