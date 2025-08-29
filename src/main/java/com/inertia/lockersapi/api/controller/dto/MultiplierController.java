package com.inertia.lockersapi.api.controller.dto;

import com.inertia.lockersapi.api.controller.dto.request.price.NewMultiplierDTO;
import com.inertia.lockersapi.domain.prices.Multiplier;
import com.inertia.lockersapi.domain.prices.service.MultiplierService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/multiplier")
public class MultiplierController {

    private final MultiplierService multiplierService;

    public MultiplierController(MultiplierService multiplierService) {
        this.multiplierService = multiplierService;
    }

    @GetMapping
    public ResponseEntity<?> getAllMultipliers(){
        return multiplierService.findAllMultipliers();
    }

    @PostMapping
    public ResponseEntity<?> addMultiplier(@RequestBody NewMultiplierDTO newMultiplierDTO){
        return ResponseEntity.ok(multiplierService.saveMultiplier(newMultiplierDTO));
    }
}
