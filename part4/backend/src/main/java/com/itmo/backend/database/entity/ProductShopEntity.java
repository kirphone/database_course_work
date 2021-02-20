package com.itmo.backend.database.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "product_shop")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductShopEntity {

    @EmbeddedId
    private ProductShopKey id;

    @ManyToOne
    @MapsId("productId")
    @JoinColumn(name = "product_id")
    private ShopEntity product;

    @ManyToOne
    @MapsId("shopId")
    @JoinColumn(name = "shop_id")
    private ShopEntity shop;

    private Float price;
}

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Data
class ProductShopKey implements Serializable {

    @Column(name = "product_id")
    private Integer productId;

    @Column(name = "shop_id")
    private Integer shopId;

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof ProductShopKey) {
            ProductShopKey other = (ProductShopKey) obj;
            return productId.equals(other.productId) && shopId.equals(other.shopId);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return productId.hashCode() + shopId.hashCode();
    }
}
