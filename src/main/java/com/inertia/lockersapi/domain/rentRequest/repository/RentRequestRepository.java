package com.inertia.lockersapi.domain.rentRequest.repository;

import com.inertia.lockersapi.domain.locker.Locker;
import com.inertia.lockersapi.domain.rentRequest.RentRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface RentRequestRepository extends JpaRepository<RentRequest, UUID> {
    List<RentRequest> findByUserId(UUID userId);
}
