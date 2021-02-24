package com.itmo.backend.database.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "discount_card")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DiscountCardEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String description;

    @Column(name = "expired_date")
    @Temporal(TemporalType.DATE)
    private Date expiredDate;

    @ManyToOne
    @JoinColumn(name = "shop_company_name")
    private ShopCompanyEntity company;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private AccountEntity account;
}
