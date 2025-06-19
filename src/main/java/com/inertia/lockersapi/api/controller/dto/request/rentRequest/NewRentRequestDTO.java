package com.inertia.lockersapi.api.controller.dto.request.rentRequest;

import jakarta.validation.constraints.NotNull;

import java.util.Date;
import java.util.UUID;

public record NewRentRequestDTO(
        @NotNull
        UUID lockerId,
        @NotNull
        UUID userId,
        @NotNull
        Date rentStartDate,
        @NotNull
        Date rentFinishDate,
        UUID openingKey) {
}
