package com.inertia.lockersapi.api.controller;

import com.inertia.lockersapi.api.controller.dto.request.NewLockerCheckOutDTO;
import com.inertia.lockersapi.api.controller.dto.request.NewLockerDTO;
import com.inertia.lockersapi.api.controller.dto.request.NewRentRequestDTO;
import com.inertia.lockersapi.domain.locker.Locker;
import com.inertia.lockersapi.domain.locker.RentRequest;
import com.inertia.lockersapi.repositories.LockerRepository;
import com.inertia.lockersapi.repositories.RentRequestRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;


@RestController
@RequestMapping("/api/locker")
public class LockerController {


    private final LockerRepository lockerRepository;
    private final RentRequestRepository rentRequestRepository;
    @Autowired
    public LockerController(LockerRepository lockerRepository, RentRequestRepository rentRequestRepository){
        this.lockerRepository = lockerRepository;
        this.rentRequestRepository = rentRequestRepository;
    }


    @PostMapping
    public ResponseEntity<?> addLocker(@RequestBody @Valid NewLockerDTO lockerDTO) {
        Locker locker = new Locker(lockerDTO);
        lockerRepository.save(locker);
        return ResponseEntity.ok(lockerDTO);

    }

    @PostMapping("/rent")
    public ResponseEntity<?> rentLocker(@RequestBody @Valid NewRentRequestDTO rentRequestDTO) {
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

    @PostMapping("/checkout")
    public ResponseEntity<?> lockerCheckOut(@RequestBody @Valid NewLockerCheckOutDTO checkOutDTO) {

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

    @GetMapping
    public ResponseEntity<?> getAllLockers(){
        return ResponseEntity.ok(lockerRepository.findAll());
    }


}
