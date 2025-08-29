package com.inertia.lockersapi.domain.prices.service;

import com.inertia.lockersapi.api.controller.dto.request.price.FinalPriceDTO;
import com.inertia.lockersapi.api.controller.dto.request.price.NewPriceDTO;
import com.inertia.lockersapi.domain.facility.Facility;
import com.inertia.lockersapi.domain.facility.repository.FacilityRepository;
import com.inertia.lockersapi.domain.locker.LockerModel;
import com.inertia.lockersapi.domain.prices.Multiplier;
import com.inertia.lockersapi.domain.prices.Price;
import com.inertia.lockersapi.domain.prices.repository.MultiplierRepository;
import com.inertia.lockersapi.domain.prices.repository.PriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PriceService {

    @Autowired
    private PriceRepository priceRepository;
    @Autowired
    private FacilityRepository facilityRepository;
    @Autowired
    private MultiplierRepository multiplierRepository;

    public PriceService(PriceRepository priceRepository) {
        this.priceRepository = priceRepository;
    }

    public ResponseEntity<?> saveprice(NewPriceDTO priceDTO){
        Price price = new Price(priceDTO);
        priceRepository.save(price);
        return ResponseEntity.ok(priceDTO);
    }

    public ResponseEntity<?> findAllPrices() {
        List<Price> prices = priceRepository.findAll();
        return ResponseEntity.ok(prices);
    }


    public ResponseEntity<?> updatePrice(UUID id, NewPriceDTO priceDTO) {
        Optional<Price> optionalPrice = priceRepository.findById(id);

        if (optionalPrice.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Preço com ID " + id + " não encontrado.");
        }

        Price price = optionalPrice.get();

        price.setLockerModel(priceDTO.lockerModel());
        price.setPriceBase(priceDTO.priceBase());

        priceRepository.save(price);

        return ResponseEntity.ok(price);
    }

    public ResponseEntity<?> findPriceByUUID(UUID uuid) {
        try {
            Optional<Price> price = priceRepository.findById(uuid);

            if (price.isPresent()) {
                return ResponseEntity.ok(price.get());
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Price not found");
            }

        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Invalid UUID format");
        }
    }
    public ResponseEntity<?> findByLockerModel(String lockerModelString) {
        try {
            LockerModel lockerModel = LockerModel.fromString(lockerModelString);
            Optional<Price> price = priceRepository.findByLockerModel(lockerModel);

            if (price.isPresent()) {
                return ResponseEntity.ok(price.get());
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Price not found");
            }

        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Modelo de Locker inválido");
        }
    }
    public  double calculateFinalPrice(UUID facilityId, int requestedTime, String lockerModelString) {
        LockerModel lockerModel = LockerModel.fromString(lockerModelString);

        Price price = priceRepository.findByLockerModel(lockerModel).orElseThrow(() -> new IllegalArgumentException("Modelo de Locker Inválido"));
        Facility facility = facilityRepository.findById(facilityId).orElseThrow(() -> new IllegalArgumentException("Facility inválido"));

        Multiplier multiplier = multiplierRepository.getByFacility(facility);

        return price.getPriceBase() * multiplier.getPriceMultiplier() * requestedTime;

    }

    public FinalPriceDTO returnFinalPrice(UUID facilityId, String lockerModelString, int requestedTime) {

        return new FinalPriceDTO(
                lockerModelString,
                facilityId,
                requestedTime,
                calculateFinalPrice(facilityId, requestedTime, lockerModelString)
        );

    }


}
