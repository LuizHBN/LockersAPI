package com.inertia.lockersapi.controller;

import com.inertia.lockersapi.domain.locker.Locker;
import com.inertia.lockersapi.domain.locker.LockerDTO;
import com.inertia.lockersapi.domain.locker.RentLocker;
import com.inertia.lockersapi.domain.locker.RentRequestLockerDTO;
import com.inertia.lockersapi.service.LockerService;
import com.inertia.lockersapi.service.RentLockerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/locker")
public class LockerController {

    @Autowired
    private LockerService lockerService;

    @Autowired
    private RentLockerService rentLockerService;

    @PostMapping
    public ResponseEntity<Locker> addLocker(@RequestBody Locker locker) {

        LockerDTO lockerRequestDTO = new LockerDTO(locker.getAddress());

        Locker newLocker = this.lockerService.registerLocker(lockerRequestDTO);

        return ResponseEntity.ok(newLocker);
    }

    @RequestMapping("/rent")
    @PostMapping
    public ResponseEntity<RentLocker> rentLocker(@RequestBody RentLocker rentLocker) {


        RentRequestLockerDTO rentRequestLockerDTO = new RentRequestLockerDTO(rentLocker.getLocker_id(), rentLocker.getUser_id());

        RentLocker newRentLocker = this.rentLockerService.rentLocker(rentRequestLockerDTO);

        return ResponseEntity.ok(newRentLocker);
    }
}
