package com.inertia.lockersapi.api.controller.dto.response;

import java.util.UUID;

public record ReadRentRequestDTO(UUID locker_id, Integer user_id) {
}
