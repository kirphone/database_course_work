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
public class OrderProductKey implements Serializable {

    @Column(name = "order_id")
    private Integer orderId;

    @Column(name = "product_id")
    private Integer productId;

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof OrderProductKey) {
            OrderProductKey other = (OrderProductKey) obj;
            return productId.equals(other.productId) && orderId.equals(other.orderId);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return productId.hashCode() + orderId.hashCode();
    }
}
