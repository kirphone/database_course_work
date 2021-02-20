package com.itmo.backend.database.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "product")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String description;

    @ManyToOne
    @JoinColumn(name = "category")
    private ProductCategoryEntity category;

    @OneToMany(mappedBy = "product")
    Set<ProductShopEntity> shopsWithThisProduct;

    @OneToMany(mappedBy = "product")
    Set<OrderProductEntity> ordersWithThisProduct;
}
