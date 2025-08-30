package com.inertia.lockersapi.domain.rentRequest.service;

import com.inertia.lockersapi.api.controller.dto.request.NewLockerCheckOutDTO;
import com.inertia.lockersapi.api.controller.dto.request.locker.NewLockerDTO;
import com.inertia.lockersapi.api.controller.dto.request.rentRequest.NewRentRequestDTO;
import com.inertia.lockersapi.api.controller.dto.response.ReadRentRequestDTO;
import com.inertia.lockersapi.domain.locker.Locker;
import com.inertia.lockersapi.domain.locker.repository.LockerRepository;
import com.inertia.lockersapi.domain.prices.service.PriceService;
import com.inertia.lockersapi.domain.rentRequest.RentRequest;
import com.inertia.lockersapi.domain.rentRequest.repository.RentRequestRepository;
import com.inertia.lockersapi.domain.transaction.Transaction;
import com.inertia.lockersapi.domain.transaction.repository.TransactionRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class RentRequestService {
    private final LockerRepository lockerRepository;
    private final RentRequestRepository rentRequestRepository;
    private final TransactionRepository transactionRepository;
    private final PriceService priceService;

    public RentRequestService(LockerRepository lockerRepository, RentRequestRepository rentRequestRepository, TransactionRepository transactionRepository, PriceService priceService) {
        this.lockerRepository = lockerRepository;
        this.rentRequestRepository = rentRequestRepository;
        this.transactionRepository = transactionRepository;
        this.priceService = priceService;
    }


    public ResponseEntity<?> rentLocker(NewRentRequestDTO rentRequestDTO){
        Locker locker = lockerRepository.findById(rentRequestDTO.lockerId())
                .orElseThrow(() -> new IllegalArgumentException("Locker not found"));

        if (locker.isFree()) {
            RentRequest rentRequest = new RentRequest(rentRequestDTO);

            rentRequest.setAmount(priceService.calculateFinalPrice(locker.getFacility().getId()
                    , rentRequest.calculateRentTime()
                    , locker.getLockerModel()));

            RentRequest savedRentRequest = rentRequestRepository.save(rentRequest);
            locker.setFree(false);
            lockerRepository.save(locker);
            return ResponseEntity.ok( new ReadRentRequestDTO(savedRentRequest));
        }

        return ResponseEntity.badRequest().body("Locker ocupado!");
    }

    public ResponseEntity<?>finishRentProcess (NewLockerCheckOutDTO checkOutDTO){
        RentRequest rentRequest = rentRequestRepository.findById(checkOutDTO.rentRequestId())
                .orElseThrow(() -> new RuntimeException("Locação não encontrada!"));

        Locker locker = lockerRepository.findById(rentRequest.getLocker().getId())
                .orElseThrow(() -> new RuntimeException("Locker não encontrado!"));

        locker.setFree(true);

        rentRequest.setRentFinishDate(new Date());


        lockerRepository.save(locker);
        rentRequestRepository.save(rentRequest);

        return ResponseEntity.ok("Check-out realizado com sucesso!");
    }


    public ResponseEntity<?> findAllRentRequests() {
        List<RentRequest> rentRequests = rentRequestRepository.findAll();

        return ResponseEntity.ok(toRentRequestDTO(rentRequests));
    }

    public ResponseEntity<?> findRentRequestByUserId(UUID userId){
        List<RentRequest> rentRequests = rentRequestRepository.findByUserId(userId);

        return ResponseEntity.ok(toRentRequestDTO(rentRequests));
    }

    public ResponseEntity<?> findRentRequestById(UUID rentRequestId){
       RentRequest rentRequest = rentRequestRepository.findById(rentRequestId).orElseThrow(() -> new IllegalArgumentException("RentRequest não encontrada!"));;
        return  ResponseEntity.ok(new ReadRentRequestDTO(rentRequest));
    }


    private static List<ReadRentRequestDTO> toRentRequestDTO(List<RentRequest> rentRequests){
       return rentRequests.stream()
                .map(ReadRentRequestDTO::new)
                .toList();
    }

}
