package com.itmo.backend.database.repositories;

import com.itmo.backend.database.entity.ProductEntity;
import com.itmo.backend.database.entity.ProductShopEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

public interface ProductShopRepository extends JpaRepository<ProductShopEntity, Integer> {

    @Transactional
    @Query("select p from ProductShopEntity p where p.shop.id = ?1")
    List<ProductShopEntity> selectProductsByShopId(Integer shopId);
}
