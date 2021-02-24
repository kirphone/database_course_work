package com.itmo.backend.database.repositories;

import com.itmo.backend.database.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface OrderRepository extends JpaRepository<OrderEntity, Integer> {

    @Transactional
    @Query("select o from OrderEntity o where o.customer.id = ?1")
    List<OrderEntity> findOrderByUserId(Integer userId);
}
