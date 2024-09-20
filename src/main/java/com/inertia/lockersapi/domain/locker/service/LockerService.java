package com.inertia.lockersapi.domain.locker.service;

import com.inertia.lockersapi.api.controller.dto.request.NewLockerCheckOutDTO;
import com.inertia.lockersapi.api.controller.dto.request.NewLockerDTO;
import com.inertia.lockersapi.api.controller.dto.request.NewRentRequestDTO;
import com.inertia.lockersapi.domain.locker.Locker;
import com.inertia.lockersapi.domain.locker.repository.LockerRepository;
import com.inertia.lockersapi.domain.rentRequest.RentRequest;
import com.inertia.lockersapi.domain.rentRequest.repository.RentRequestRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Date;
import java.util.List;

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
    public ResponseEntity<?> savelocker(NewLockerDTO lockerDTO){
        Locker locker = new Locker(lockerDTO);
        lockerRepository.save(locker);
        return ResponseEntity.ok(lockerDTO);
    }

    public ResponseEntity<?> finishRentProcess (NewLockerCheckOutDTO checkOutDTO){
        RentRequest rentRequest = rentRequestRepository.findById(checkOutDTO.rentRequestId())
                .orElseThrow(() -> new RuntimeException("Locação não encontrada!"));

        Locker locker = lockerRepository.findById(rentRequest.getLocker_id())
                .orElseThrow(() -> new RuntimeException("Locker não encontrado!"));

        locker.setFree(true);

        rentRequest.setRentFinishDate(new Date());


        lockerRepository.save(locker);
        rentRequestRepository.save(rentRequest);

        return ResponseEntity.ok("Check-out realizado com sucesso!");
    }

    public ResponseEntity<?> findAllLockers() {
        List<Locker> lockers = lockerRepository.findAll();
        return ResponseEntity.ok(lockers);
    }
    public ResponseEntity<?> findAllRentRequests(){
        List<RentRequest> rentRequests = rentRequestRepository.findAll();
        return ResponseEntity.ok(rentRequests);
    }


}