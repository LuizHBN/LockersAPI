package com.inertia.lockersapi.api.controller;

import com.inertia.lockersapi.api.controller.dto.request.price.NewPriceDTO;
import com.inertia.lockersapi.domain.prices.service.PriceService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/price")
public class PriceController {

    private final PriceService priceService;

    public PriceController(PriceService priceService) {
        this.priceService = priceService;
    }


    @GetMapping
    public ResponseEntity<?> getPrice(){
        return ResponseEntity.ok(priceService.findAllPrices());
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> getPriceById(@PathVariable UUID id){
        return ResponseEntity.ok(priceService.findPriceByUUID(id));
    }


    @PostMapping
    public ResponseEntity<?> addPrice(@RequestBody @Valid NewPriceDTO newPriceDTO){
        return ResponseEntity.ok(priceService.saveprice(newPriceDTO));
    }



    @GetMapping("/final")
    public ResponseEntity<?> getFinalPrice(@RequestParam UUID facilityId, @RequestParam String lockerModel, @RequestParam int requestedTime){
        return  ResponseEntity.ok(priceService.returnFinalPrice(facilityId, lockerModel, requestedTime));

    }


}
