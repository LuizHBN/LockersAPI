package com.inertia.lockersapi.domain.prices.service;

import com.inertia.lockersapi.api.controller.dto.request.price.NewMultiplierDTO;
import com.inertia.lockersapi.api.controller.dto.request.price.NewPriceDTO;
import com.inertia.lockersapi.domain.facility.Facility;
import com.inertia.lockersapi.domain.facility.repository.FacilityRepository;
import com.inertia.lockersapi.domain.prices.Multiplier;

import com.inertia.lockersapi.domain.prices.Price;
import com.inertia.lockersapi.domain.prices.repository.MultiplierRepository;
import com.inertia.lockersapi.domain.prices.repository.PriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class MultiplierService {
    private final PriceRepository priceRepository;
    private final FacilityRepository facilityRepository;
    private MultiplierRepository multiplierRepository;
    @Autowired
    public MultiplierService(MultiplierRepository multiplierRepository, PriceRepository priceRepository, FacilityRepository facilityRepository) {
        this.multiplierRepository = multiplierRepository;
        this.priceRepository = priceRepository;
        this.facilityRepository = facilityRepository;
    }

    public ResponseEntity<?> findAllMultipliers(){
        return  ResponseEntity.ok(multiplierRepository.findAll());
    }

    public ResponseEntity<Multiplier> saveMultiplier(NewMultiplierDTO newMultiplierDTO) {
        Multiplier multiplier = new Multiplier(newMultiplierDTO);


        Facility facility = facilityRepository.findById(newMultiplierDTO.facilityId())
                .orElseThrow(() -> new RuntimeException("Facility not found"));

        multiplier.setFacility(facility);

        Multiplier savedMultiplier = multiplierRepository.save(multiplier);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedMultiplier);
    }


    public ResponseEntity<?> findMultiplierByUUID(String uuid) {
        try {
            UUID id = UUID.fromString(uuid);
                Optional<Multiplier> multiplier = multiplierRepository.findById(id);

            if (multiplier.isPresent()) {
                return ResponseEntity.ok(multiplier.get());
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Multiplier not found");
            }

        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Invalid UUID format");
        }
    }

    public ResponseEntity<?> updateMultiplier(UUID id, NewMultiplierDTO multiplierDTO) {
        Optional<Multiplier> optionalMultiplier = multiplierRepository.findById(id);

        if (optionalMultiplier.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Multiplicador com ID " + id + " não encontrado.");
        }

        Multiplier multiplier = optionalMultiplier.get();

        multiplier.setPriceMultiplier(multiplierDTO.priceMultiplier());
        Facility facility = new Facility();
        facility.setId(multiplierDTO.facilityId());
        multiplier.setFacility(facility);

        multiplierRepository.save(multiplier);

        return ResponseEntity.status(HttpStatus.CREATED).body(multiplierRepository.save(multiplier));
    }
}
