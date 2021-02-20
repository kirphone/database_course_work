package com.itmo.backend.database.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Table(name = "shop_company")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ShopCompanyEntity {

    @Id
    private String name;

    @OneToMany(mappedBy = "company")
    private Set<ShopEntity> shops;

    @OneToMany(mappedBy = "company")
    private Set<DiscountCardEntity> discountCards;
}
