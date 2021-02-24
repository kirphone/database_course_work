package com.itmo.backend.database.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "account")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property="id")
@JsonIgnoreProperties(value = {"messages", "discountCards", "ordersAsCourier", "ordersAsCustomer"})
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
