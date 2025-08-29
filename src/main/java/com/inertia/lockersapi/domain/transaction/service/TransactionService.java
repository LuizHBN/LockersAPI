package com.inertia.lockersapi.domain.transaction.service;

import com.inertia.lockersapi.api.controller.dto.request.transaction.NewTransactionDTO;
import com.inertia.lockersapi.api.controller.dto.response.ReadTransactionDTO;
import com.inertia.lockersapi.domain.facility.Facility;
import com.inertia.lockersapi.domain.facility.repository.FacilityRepository;
import com.inertia.lockersapi.domain.locker.Locker;
import com.inertia.lockersapi.domain.locker.repository.LockerRepository;
import com.inertia.lockersapi.domain.prices.service.PriceService;
import com.inertia.lockersapi.domain.rentRequest.RentRequest;
import com.inertia.lockersapi.domain.rentRequest.repository.RentRequestRepository;
import com.inertia.lockersapi.domain.transaction.Transaction;
import com.inertia.lockersapi.domain.transaction.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TransactionService {
    @Autowired
    private final TransactionRepository transactionRepository;
    @Autowired
    private final FacilityRepository facilityRepository;
    private final RentRequestRepository rentRequestRepository;
    private PriceService priceService;
    @Autowired
    private LockerRepository lockerRepository;

    public TransactionService(TransactionRepository transactionRepository, FacilityRepository facilityRepository, PriceService priceService,
                              RentRequestRepository rentRequestRepository) {
        this.transactionRepository = transactionRepository;
        this.priceService = priceService;
        this.rentRequestRepository = rentRequestRepository;
        this.facilityRepository = facilityRepository;
    }

    public ResponseEntity<?> findAllTransactions() {
        return ResponseEntity.ok(toReadTransactionDTO(transactionRepository.findAll()));
    }

    public ResponseEntity<?> saveTransaction(NewTransactionDTO newTransactionDTO) {
        RentRequest rentRequest = rentRequestRepository.findById(newTransactionDTO.rentRequestId()).orElseThrow(() -> new IllegalArgumentException("RentRequest Not Found"));
        int rentTime = rentRequest.calculateRentTime();
        Locker locker = rentRequest.getLocker();
        String lockerModel = locker.getLockerModel();
        UUID facilityId = locker.getFacility().getId();

        Transaction transaction = new Transaction(newTransactionDTO);
        transaction.setAmount(priceService.calculateFinalPrice(facilityId,rentTime,lockerModel));

       return ResponseEntity.ok(
               new ReadTransactionDTO(
                       transactionRepository.save(
                               transaction)));
    }

    private static List<ReadTransactionDTO> toReadTransactionDTO(List<Transaction> transactions){
        return transactions.stream()
                .map(ReadTransactionDTO::new)
                .toList();
    }


}
