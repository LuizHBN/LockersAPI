package com.inertia.lockersapi.domain.prices.repository;

import com.inertia.lockersapi.domain.locker.LockerModel;
import com.inertia.lockersapi.domain.prices.Price;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface PriceRepository extends JpaRepository<Price, UUID> {
    Optional<Price> findByLockerModel(LockerModel lockerModel);
}
