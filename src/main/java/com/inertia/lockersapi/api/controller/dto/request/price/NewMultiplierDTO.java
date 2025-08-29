package com.inertia.lockersapi.api.controller.dto.request.price;

import com.inertia.lockersapi.domain.facility.Facility;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record NewMultiplierDTO(
        @NotNull
        UUID facilityId,
        @NotNull
        double priceMultiplier
) {
}
