package com.inertia.lockersapi.domain.prices;

import com.inertia.lockersapi.api.controller.dto.request.price.NewPriceDTO;
import com.inertia.lockersapi.domain.locker.LockerModel;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.UniqueConstraint;
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
    private LockerModel lockerModel;
    private double priceBase;

    public Price(NewPriceDTO priceDTO) {
        this.priceBase = priceDTO.priceBase();
        this.lockerModel = priceDTO.lockerModel();
    }
}
