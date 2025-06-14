package com.inertia.lockersapi.api.controller.dto.request.facility;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record NewFacilityDTO(
        @NotNull
        @NotBlank
        String address,
        @NotNull
        String cep
) {
}
