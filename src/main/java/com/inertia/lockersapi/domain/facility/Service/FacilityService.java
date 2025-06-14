package com.inertia.lockersapi.domain.facility.Service;

import com.inertia.lockersapi.api.controller.dto.request.facility.NewFacilityDTO;
import com.inertia.lockersapi.api.controller.dto.request.locker.NewLockerDTO;
import com.inertia.lockersapi.domain.facility.Facility;
import com.inertia.lockersapi.domain.facility.repository.FacilityRepository;
import com.inertia.lockersapi.domain.locker.Locker;
import com.inertia.lockersapi.domain.locker.repository.LockerRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class FacilityService {

    private final FacilityRepository facilityRepository;

    public    FacilityService(FacilityRepository facilityRepository) {
        this.facilityRepository = facilityRepository;
    }

    public ResponseEntity<?> savefacility(NewFacilityDTO facilityDTO){
        Facility facility = new Facility(facilityDTO);
        facilityRepository.save(facility);
        return ResponseEntity.ok(facilityDTO);
    }

    public ResponseEntity<?> findAllFacilities() {
        List<Facility> facilities = facilityRepository.findAll();
        return ResponseEntity.ok(facilities);
    }

    public ResponseEntity<?> findFacilityByUUID(String uuid) {
        try {
            UUID id = UUID.fromString(uuid);
            Optional<Facility> facility = facilityRepository.findById(id);

            if (facility.isPresent()) {
                return ResponseEntity.ok(facility.get());
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Facility not found");
            }

        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Invalid UUID format");
        }
    }

}
