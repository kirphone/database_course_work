package com.itmo.backend.database.repositories;

import com.itmo.backend.database.entity.ShopCompanyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShopCompanyRepository extends JpaRepository<ShopCompanyEntity, String> {
}
