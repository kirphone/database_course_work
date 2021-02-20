package com.itmo.backend.database.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.OffsetDateTime;

@Entity
@Table(name = "message")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class MessageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "message_text")
    private String text;

    @Column(name = "send_datetime", columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private OffsetDateTime sendTime;

    @ManyToOne
    @JoinColumn(name = "sender_id")
    private AccountEntity sender;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private OrderEntity order;
}
