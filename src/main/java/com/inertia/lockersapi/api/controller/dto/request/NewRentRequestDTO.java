package com.inertia.lockersapi.api.controller.dto.request;

import jakarta.validation.constraints.NotNull;

import java.util.Date;
import java.util.UUID;

public record NewRentRequestDTO(
        @NotNull
        UUID lockerId,
        @NotNull
        Integer userId,
        @NotNull
        Date rentStartDate,
        @NotNull
        Date rentFinishDate) {
}
