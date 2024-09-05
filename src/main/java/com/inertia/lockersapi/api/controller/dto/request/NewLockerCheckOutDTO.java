package com.inertia.lockersapi.api.controller.dto.request;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record NewLockerCheckOutDTO(
        @NotNull
        int userId,
        @NotNull
        UUID rentRequestId
) {
}
