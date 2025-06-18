package com.inertia.lockersapi.domain.user.repository;

import com.inertia.lockersapi.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
}
