package com.inertia.lockersapi.api.controller.dto.request.facility;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.aspectj.weaver.ast.Not;

import java.util.UUID;

public record NewFacilityDTO(
        @NotNull
        @NotBlank
        String address,
        @NotNull
        String cep,
        @NotNull
        String lat,
        @NotNull
        String lon
) {
}
