package com.itmo.backend.database.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "order")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Embedded
    AddressEmbeddedEntity address;

    @ManyToOne
    @JoinColumn(name = "shop_id")
    private ShopEntity shop;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private AccountEntity customer;

    @ManyToOne
    @JoinColumn(name = "courier_id")
    private AccountEntity courier;

    @ManyToOne
    @JoinColumn(name = "status")
    private StatusOrderEntity status;

    @OneToMany(mappedBy = "order")
    private Set<MessageEntity> messages;

    @OneToMany(mappedBy = "order")
    Set<OrderProductEntity> products;

    @OneToOne(mappedBy = "order")
    ReviewEntity review;
}
