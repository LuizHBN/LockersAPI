package com.inertia.lockersapi.api.controller.dto.response;

import com.inertia.lockersapi.domain.rentRequest.RentRequest;

import java.util.Date;
import java.util.UUID;

public record ReadRentRequestDTO(UUID lockerId, UUID userId, Date rentStartDate, Date rentFinishDate) {
    public ReadRentRequestDTO(RentRequest rentRequest) {
        this(
                rentRequest.getLocker() != null ? rentRequest.getLocker().getId() : null,
                rentRequest.getUser() != null ? rentRequest.getUser().getId() : null,
                rentRequest.getRentStartDate(),
                rentRequest.getRentFinishDate()
        );
    }
}

