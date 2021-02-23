package com.itmo.backend.database.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "shop_company")
@AllArgsConstructor
@NoArgsConstructor
public class ShopCompanyEntity {

    public ShopCompanyEntity(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShopCompanyEntity that = (ShopCompanyEntity) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "ShopCompanyEntity{" +
                "name='" + name + '\'' +
                '}';
    }

    @Id
    private String name;

    @OneToMany(mappedBy = "company")
    private Set<ShopEntity> shops;

    @OneToMany(mappedBy = "company")
    private Set<DiscountCardEntity> discountCards;
}
