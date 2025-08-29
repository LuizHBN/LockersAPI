package com.inertia.lockersapi.domain.prices;

import com.inertia.lockersapi.api.controller.dto.request.price.NewPriceDTO;
import com.inertia.lockersapi.domain.facility.Facility;
import com.inertia.lockersapi.domain.locker.LockerModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.mapping.UniqueKey;
import org.hibernate.validator.constraints.UniqueElements;

import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Price {
    @Id
    @GeneratedValue
    UUID id;
    @ManyToOne(fetch = FetchType.LAZY)
    private Facility facility;
    private LockerModel lockerModel;
    private double priceBase;

    public Price(NewPriceDTO priceDTO) {
        Facility facility = new Facility();
        facility.setId(priceDTO.facilityID());
        this.facility = facility;
        this.priceBase = priceDTO.priceBase();
        this.lockerModel = priceDTO.lockerModel();
    }
}
