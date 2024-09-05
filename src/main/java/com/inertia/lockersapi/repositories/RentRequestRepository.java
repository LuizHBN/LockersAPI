package com.inertia.lockersapi.repositories;

import com.inertia.lockersapi.domain.locker.RentRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RentRequestRepository extends JpaRepository<RentRequest, UUID> {
}
