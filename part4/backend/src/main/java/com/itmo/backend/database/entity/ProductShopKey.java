package com.itmo.backend.database.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductShopKey implements Serializable {

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
