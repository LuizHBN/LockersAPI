package com.inertia.lockersapi.api.controller.dto.response;

import com.inertia.lockersapi.domain.facility.Facility;

import java.util.UUID;

public record ReadFacilityDTO(UUID id, String address, String cep, String lat, String lon) {
    public ReadFacilityDTO(Facility facility){
        this(
                facility.getId(),
                facility.getAddress(),
                facility.getCep(),
                facility.getLat(),
                facility.getLon()
        );
    }
}
