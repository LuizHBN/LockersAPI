package com.inertia.lockersapi.api.controller.dto.request.price;

import com.inertia.lockersapi.domain.locker.LockerModel;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record NewPriceDTO(
        @NotNull
        UUID facilityID,
        @NotNull
        LockerModel lockerModel,
        @NotNull
        @Min(0)
        double priceBase
) {

}
