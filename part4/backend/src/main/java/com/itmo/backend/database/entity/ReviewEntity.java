package com.itmo.backend.database.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "review")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ReviewEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String description;

    private Integer rate;

    @OneToOne
    @JoinColumn(name = "order_id")
    private OrderEntity order;
}
