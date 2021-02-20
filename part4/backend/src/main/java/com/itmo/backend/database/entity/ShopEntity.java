package com.itmo.backend.database.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "shop")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ShopEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @Embedded
    AddressEmbeddedEntity address;

    @ManyToOne
    @JoinColumn(name = "company_name")
    private ShopCompanyEntity company;

    @OneToMany(mappedBy = "shop")
    Set<ProductShopEntity> productsInThisShop;

    @OneToMany(mappedBy = "shop")
    Set<OrderEntity> orders;
}
