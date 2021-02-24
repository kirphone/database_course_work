package com.itmo.backend.database.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "order_product")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
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

