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
@Table(name = "product_category")
@JsonIgnoreProperties(value = {"products"})
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductCategoryEntity {

    @Id
    private String name;

    @OneToMany(mappedBy = "category")
    private Set<ProductEntity> products;
}
