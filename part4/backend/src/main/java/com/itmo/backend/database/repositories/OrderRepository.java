package com.itmo.backend.database.repositories;

import com.itmo.backend.database.entity.AccountEntity;
import com.itmo.backend.database.entity.AddressEmbeddedEntity;
import com.itmo.backend.database.entity.OrderEntity;
import com.itmo.backend.database.entity.StatusOrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<OrderEntity, Integer> {

    @Transactional
    @Query("select o from OrderEntity o where o.customer.id = ?1")
    List<OrderEntity> findOrderByUserId(Integer userId);

    Optional<OrderEntity> findById(Integer integer);

    @Transactional
    @Query(value = "select o.address, o.id from OrderEntity o where o.status = 'Поиск курьера'")
    List<Object[]> findAllWithoutCourier();

    @Transactional
    @Modifying
    @Query("update OrderEntity o set o.status = ?1, o.courier = ?2 where o.id = ?3")
    int updateStatusAndCourier(StatusOrderEntity status, AccountEntity courier, Integer id);

    @Transactional
    @Query("select o.customer.name, o.customer.phone from OrderEntity o where o.id = ?1")
    Object[] getCustomerNameAndPhoneByOrderId(Integer id);
}
