package com.itmo.backend.database.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "product_shop")
@JsonIgnoreProperties(value = {"shop"})
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductShopEntity {

    @EmbeddedId
    private ProductShopKey id;

    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("productId")
    @JoinColumn(name = "product_id")
    private ProductEntity product;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductShopEntity that = (ProductShopEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(price, that.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, price);
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("shopId")
    @JoinColumn(name = "shop_id")
    private ShopEntity shop;

    private Float price;
}

