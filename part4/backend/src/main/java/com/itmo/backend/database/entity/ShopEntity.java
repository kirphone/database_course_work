package com.itmo.backend.database.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "shop")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property="id")
@JsonIgnoreProperties(value = {"productsInThisShop", "orders", "company"})
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShopEntity that = (ShopEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @OneToMany(mappedBy = "shop")
    Set<ProductShopEntity> productsInThisShop;

    @OneToMany(mappedBy = "shop")
    Set<OrderEntity> orders;
}
