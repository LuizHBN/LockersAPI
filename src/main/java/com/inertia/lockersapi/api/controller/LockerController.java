package com.inertia.lockersapi.api.controller;

import com.inertia.lockersapi.api.controller.dto.request.NewLockerCheckOutDTO;
import com.inertia.lockersapi.api.controller.dto.request.locker.NewLockerDTO;
import com.inertia.lockersapi.api.controller.dto.request.rentRequest.NewRentRequestDTO;
import com.inertia.lockersapi.domain.locker.service.LockerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/locker")
public class LockerController {


    private final LockerService lockerService;

    @Autowired
    public LockerController(LockerService lockerService){
        this.lockerService = lockerService;
    }


    @PostMapping
    public ResponseEntity<?> addLocker(@RequestBody @Valid NewLockerDTO lockerDTO) {
        return lockerService.savelocker(lockerDTO);
    }

    @PostMapping("/rent")
    public ResponseEntity<?> rentLocker(@RequestBody @Valid NewRentRequestDTO rentRequestDTO) {
        return lockerService.rentLocker(rentRequestDTO);
    }

    @GetMapping
    public ResponseEntity<?> getAllLockers(){
        return lockerService.findAllLockers();
    }

    @GetMapping("/facility/id/{facilityID}")
    public ResponseEntity<?> getAllLockersByFacilityID(@PathVariable String facilityID) {
        return lockerService.findLockerByFacilityId(facilityID);
    }


}
