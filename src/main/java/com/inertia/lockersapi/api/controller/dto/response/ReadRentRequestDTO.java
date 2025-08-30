package com.inertia.lockersapi.api.controller.dto.response;

import com.inertia.lockersapi.domain.rentRequest.RentRequest;

import java.util.Date;
import java.util.UUID;

public record ReadRentRequestDTO(UUID rentRequestId,UUID lockerId, UUID userId, Date rentStartDate, Date rentFinishDate,double amount, UUID openingKey) {
    public ReadRentRequestDTO(RentRequest rentRequest) {
        this(
                rentRequest.getId(),
                rentRequest.getLocker() != null ? rentRequest.getLocker().getId() : null,
                rentRequest.getUser() != null ? rentRequest.getUser().getId() : null,
                rentRequest.getRentStartDate(),
                rentRequest.getRentFinishDate(),
                rentRequest.getAmount(),
                rentRequest.getOpeningKey()

        );
    }
}

