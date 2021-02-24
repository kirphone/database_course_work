package com.itmo.backend.database.repositories;

import com.itmo.backend.database.entity.MessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface MessageRepository extends JpaRepository<MessageEntity, Integer> {

    @Transactional
    @Query("select m from MessageEntity m where m.order.id = ?1")
    List<MessageEntity> findByOrderId(Integer orderId);
}
