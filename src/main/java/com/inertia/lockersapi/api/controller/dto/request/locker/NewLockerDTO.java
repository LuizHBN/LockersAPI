package com.inertia.lockersapi.api.controller.dto.request.locker;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record NewLockerDTO(
        @NotNull
        boolean isFree,
        @NotNull
        UUID facilityID
) {
}
