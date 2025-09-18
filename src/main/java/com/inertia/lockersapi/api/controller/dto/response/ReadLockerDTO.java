package com.inertia.lockersapi.api.controller.dto.response;

import com.inertia.lockersapi.domain.facility.Facility;
import com.inertia.lockersapi.domain.locker.Locker;

import java.util.UUID;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public record ReadLockerDTO(UUID id, ReadFacilityDTO facility, String alias, String lockerModel) {

    public ReadLockerDTO( Locker locker){
        this(
                locker.getId(),
                new ReadFacilityDTO(locker.getFacility()),
                locker.getAlias(),
                locker.getLockerModel()
        );
    }
}
