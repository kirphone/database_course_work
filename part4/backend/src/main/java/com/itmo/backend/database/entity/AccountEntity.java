package com.itmo.backend.database.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "account")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class AccountEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String phone;

    private String email;

    private String password;

    @OneToMany(mappedBy = "customer")
    private Set<OrderEntity> ordersAsCustomer;

    @OneToMany(mappedBy = "courier")
    private Set<OrderEntity> ordersAsCourier;

    @OneToMany(mappedBy = "sender")
    private Set<MessageEntity> messages;

    @OneToMany(mappedBy = "account")
    private Set<DiscountCardEntity> discountCards;
}
