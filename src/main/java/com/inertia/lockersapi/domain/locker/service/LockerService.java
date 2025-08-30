package com.inertia.lockersapi.domain.locker.service;

import com.inertia.lockersapi.api.controller.dto.request.NewLockerCheckOutDTO;
import com.inertia.lockersapi.api.controller.dto.request.locker.NewLockerDTO;
import com.inertia.lockersapi.api.controller.dto.request.rentRequest.NewRentRequestDTO;
import com.inertia.lockersapi.domain.locker.Locker;
import com.inertia.lockersapi.domain.locker.repository.LockerRepository;
import com.inertia.lockersapi.domain.rentRequest.RentRequest;
import com.inertia.lockersapi.domain.rentRequest.repository.RentRequestRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class LockerService {
    private final LockerRepository lockerRepository;
    private final RentRequestRepository rentRequestRepository;

    public LockerService(LockerRepository lockerRepository, RentRequestRepository rentRequestRepository){
        this.lockerRepository = lockerRepository;
        this.rentRequestRepository = rentRequestRepository;
    }

    public ResponseEntity<?> rentLocker(NewRentRequestDTO rentRequestDTO){
        Locker locker = lockerRepository.findById(rentRequestDTO.lockerId())
                .orElseThrow(() -> new RuntimeException("Locker not found"));

        if (locker.isFree()) {
            RentRequest rentRequest = new RentRequest(rentRequestDTO);
            locker.setFree(false);
            lockerRepository.save(locker);
            rentRequestRepository.save(rentRequest);
            return ResponseEntity.ok(rentRequest);
        }

        return ResponseEntity.badRequest().body("Locker ocupado!");
    }

    public ResponseEntity<?> saveLocker(NewLockerDTO lockerDTO) {
        Locker locker = new Locker(lockerDTO);
        try {
            lockerRepository.save(locker);
            return ResponseEntity.ok(locker);
        } catch (DataIntegrityViolationException ex) {
            return ResponseEntity
                    .badRequest()
                    .body("Erro ao salvar locker: " + ex.getMostSpecificCause().getMessage());
        }
    }

    public ResponseEntity<?> findAllLockers() {
        List<Locker> lockers = lockerRepository.findAll();
        return ResponseEntity.ok(lockers);
    }
    public ResponseEntity<?> findAllRentRequests(){
        List<RentRequest> rentRequests = rentRequestRepository.findAll();
        return ResponseEntity.ok(rentRequests);
    }

    public ResponseEntity<?> findLockerByFacilityId(String facilityId){
        UUID facilityUUID = UUID.fromString(facilityId);
        List<Locker> lockers = lockerRepository.findByFacilityId(facilityUUID);
        return ResponseEntity.ok(lockers);
    }


}