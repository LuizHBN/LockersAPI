package com.inertia.lockersapi.domain.facility.repository;

import com.inertia.lockersapi.domain.facility.Facility;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface FacilityRepository extends JpaRepository<Facility, UUID> {
}
