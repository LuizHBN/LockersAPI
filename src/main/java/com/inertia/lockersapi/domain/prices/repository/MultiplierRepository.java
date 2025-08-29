package com.inertia.lockersapi.domain.prices.repository;

import com.inertia.lockersapi.domain.facility.Facility;
import com.inertia.lockersapi.domain.prices.Multiplier;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MultiplierRepository extends JpaRepository<Multiplier, UUID> {
    Multiplier getByFacility(Facility facility);
}
