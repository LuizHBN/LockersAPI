package com.inertia.lockersapi.api.controller.dto.request.locker;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.aspectj.weaver.ast.Not;

import java.util.UUID;

public record NewLockerDTO(
        @NotNull
        boolean isFree,
        @NotNull
        UUID facilityID,
        @NotNull
        String alias,
        @NotNull
        String lockerModel
) {
}
