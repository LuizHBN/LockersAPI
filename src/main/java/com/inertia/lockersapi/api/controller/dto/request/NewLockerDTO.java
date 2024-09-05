package com.inertia.lockersapi.api.controller.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record NewLockerDTO(
        @NotNull
        @NotBlank
        String address,
        @NotNull
        boolean isFree
) {
}
