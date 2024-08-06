package com.inertia.lockersapi.domain.locker;

import java.util.UUID;

public record RentRequestLockerDTO(UUID locker_id, Integer user_id) {
}
