package com.itmo.backend.database.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Table(name = "status_order")
@JsonIgnoreProperties(value = {"orders"})
@AllArgsConstructor
@NoArgsConstructor
@Data
public class StatusOrderEntity {

    @Id
    private String name;

    private String description;

    @OneToMany(mappedBy = "status")
    private Set<OrderEntity> orders;
}
