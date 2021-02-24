package com.itmo.backend.database.repositories;

import com.itmo.backend.database.entity.AccountEntity;
import com.itmo.backend.database.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface AccountRepository extends JpaRepository<AccountEntity, Integer> {

    Optional<AccountEntity> findByEmail(String email);
    Optional<AccountEntity> findById(Integer id);
    Optional<AccountEntity> findByPhone(String phone);
}
