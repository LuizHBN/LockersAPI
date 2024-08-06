package com.inertia.lockersapi.repositories;

import com.inertia.lockersapi.domain.locker.Locker;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface LockerRepository extends JpaRepository<Locker, UUID> {
}