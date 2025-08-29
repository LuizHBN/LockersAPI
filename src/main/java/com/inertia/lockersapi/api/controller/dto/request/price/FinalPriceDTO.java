package com.inertia.lockersapi.api.controller.dto.request.price;

import com.inertia.lockersapi.domain.locker.LockerModel;

import java.util.UUID;

public record FinalPriceDTO(
        String lockerModel,
        UUID facilityID,
        int requestedTime,
        double finalPrice
) {
}
