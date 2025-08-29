package com.inertia.lockersapi.api.controller.dto.response;

import com.inertia.lockersapi.domain.locker.LockerModel;
import com.inertia.lockersapi.domain.prices.Price;

import java.util.UUID;

public record ReadPriceDTO(
        UUID id,
        UUID facilityID,
        LockerModel lockerModel,
        double priceBase
) {
    public ReadPriceDTO(Price price) {
        this(
                price.getId(),
                price.getFacility().getId(),
                price.getLockerModel(),
                price.getPriceBase()
        );
    }
}