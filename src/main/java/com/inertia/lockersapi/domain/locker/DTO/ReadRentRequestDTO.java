package com.inertia.lockersapi.domain.locker.DTO;

import java.util.UUID;

public record ReadRentRequestDTO(UUID locker_id, Integer user_id) {
}
