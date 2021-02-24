package com.itmo.backend.database.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "product")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property="id")
@JsonIgnoreProperties(value = {"ordersWithThisProduct", "shopsWithThisProduct"})
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
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
