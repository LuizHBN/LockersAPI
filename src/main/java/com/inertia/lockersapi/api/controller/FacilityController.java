package com.inertia.lockersapi.api.controller;

import com.inertia.lockersapi.api.controller.dto.request.facility.NewFacilityDTO;
import com.inertia.lockersapi.api.controller.dto.request.locker.NewLockerDTO;
import com.inertia.lockersapi.domain.facility.Service.FacilityService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/facility")
public class FacilityController {

    private final FacilityService facilityService;

    public FacilityController(FacilityService facilityService) {
        this.facilityService = facilityService;
    }

    @PostMapping("/add")
    public ResponseEntity<?> addFacility(@RequestBody @Valid NewFacilityDTO facilityDTO) {
        return facilityService.savefacility(facilityDTO);
    }

    @GetMapping
    public ResponseEntity<?> getAllFacilities () {
        return facilityService.findAllFacilities();
    }
}
