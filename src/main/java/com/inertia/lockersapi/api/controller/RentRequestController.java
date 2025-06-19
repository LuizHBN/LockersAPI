package com.inertia.lockersapi.api.controller;

import com.inertia.lockersapi.api.controller.dto.request.NewLockerCheckOutDTO;
import com.inertia.lockersapi.api.controller.dto.request.rentRequest.NewRentRequestDTO;
import com.inertia.lockersapi.domain.rentRequest.service.RentRequestService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


@RestController
@RequestMapping("/api/rent")
public class RentRequestController {


    private final RentRequestService rentRequestService;

    @Autowired
    public RentRequestController(RentRequestService lockerService){
        this.rentRequestService = lockerService;
    }


    @PostMapping()
    public ResponseEntity<?> rentLocker(@RequestBody @Valid NewRentRequestDTO rentRequestDTO) {
        return rentRequestService.rentLocker(rentRequestDTO);
    }

    @PostMapping("/checkout")
    public ResponseEntity<?> lockerCheckOut(@RequestBody @Valid NewLockerCheckOutDTO checkOutDTO) {
        return rentRequestService.finishRentProcess(checkOutDTO);
    }

    @GetMapping()
    public ResponseEntity<?> getAllRentRequests(){
        return rentRequestService.findAllRentRequests();
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getRentRequestByUserId(@PathVariable UUID userId){
        return rentRequestService.findRentRequestByUserId(userId);
    }
    @GetMapping("/{rentRequestId}")
    public ResponseEntity<?> getRentRequestById(@PathVariable UUID rentRequestId){
        return rentRequestService.findRentRequestByUserId(rentRequestId);
    }



}
