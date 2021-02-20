package com.itmo.backend.database.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "order_product")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderProductEntity {

    @EmbeddedId
    private OrderProductKey id;

    @ManyToOne
    @MapsId("productId")
    @JoinColumn(name = "product_id")
    private ProductEntity product;

    @ManyToOne
    @MapsId("orderId")
    @JoinColumn(name = "order_id")
    private OrderEntity order;

    private Float price;

    @Column(name = "product_count")
    private Integer productCount;

    @Column(name = "need_confirm")
    private Boolean needConfirm;
}

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Data
class OrderProductKey implements Serializable {

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
