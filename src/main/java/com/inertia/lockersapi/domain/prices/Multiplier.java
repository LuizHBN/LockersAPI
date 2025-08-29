package com.inertia.lockersapi.domain.prices;

import com.inertia.lockersapi.api.controller.dto.request.price.NewMultiplierDTO;
import com.inertia.lockersapi.domain.facility.Facility;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Multiplier {
    @Id
    @GeneratedValue
    private UUID id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "facility_id")
    private Facility facility;
    private double priceMultiplier;

    public Multiplier(NewMultiplierDTO newMultiplierDTO) {
        this.priceMultiplier = newMultiplierDTO.priceMultiplier();
        this.facility = new Facility();
        this.facility.setId(newMultiplierDTO.facilityId());
    }
}
