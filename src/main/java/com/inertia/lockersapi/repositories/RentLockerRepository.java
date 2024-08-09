package com.inertia.lockersapi.repositories;

import com.inertia.lockersapi.domain.locker.RentLocker;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RentLockerRepository extends JpaRepository<RentLocker, Integer> {
}
