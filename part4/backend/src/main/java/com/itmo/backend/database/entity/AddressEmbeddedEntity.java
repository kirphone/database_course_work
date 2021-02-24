package com.itmo.backend.database.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AddressEmbeddedEntity {

    @Column(name = "address_lat")
    private Double addressLat;

    @Column(name = "address_lng")
    private Double addressLng;

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof AddressEmbeddedEntity) {
            AddressEmbeddedEntity other = (AddressEmbeddedEntity) obj;
            return addressLat.equals(other.addressLat) && addressLng.equals(other.addressLng);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return addressLat.hashCode() + addressLng.hashCode();
    }
}
