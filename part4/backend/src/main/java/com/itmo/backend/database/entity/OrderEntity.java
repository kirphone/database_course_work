package com.itmo.backend.database.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "order")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property="id")
@JsonIgnoreProperties(value = {"shop", "courier", "customer", "messages", "products", "review"})
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Embedded
    AddressEmbeddedEntity address;

    @ManyToOne
    @JoinColumn(name = "shop_id")
    private ShopEntity shop;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id")
    private AccountEntity customer;

    @ManyToOne
    @JoinColumn(name = "courier_id")
    private AccountEntity courier;

    @ManyToOne
    @JsonIdentityReference(alwaysAsId = true)
    @JoinColumn(name = "status")
    private StatusOrderEntity status;

    @OneToMany(mappedBy = "order")
    private Set<MessageEntity> messages;

    @OneToMany(mappedBy = "order")
    Set<OrderProductEntity> products;

    @OneToOne(mappedBy = "order")
    ReviewEntity review;
}
