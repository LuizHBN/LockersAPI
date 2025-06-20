package com.inertia.lockersapi.domain.transaction.service;

import com.inertia.lockersapi.api.controller.dto.request.transaction.NewTransactionDTO;
import com.inertia.lockersapi.api.controller.dto.response.ReadRentRequestDTO;
import com.inertia.lockersapi.api.controller.dto.response.ReadTransactionDTO;
import com.inertia.lockersapi.domain.rentRequest.RentRequest;
import com.inertia.lockersapi.domain.rentRequest.service.RentRequestService;
import com.inertia.lockersapi.domain.transaction.Transaction;
import com.inertia.lockersapi.domain.transaction.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public ResponseEntity<?> findAllTransactions() {
        return ResponseEntity.ok(toReadTransactionDTO(transactionRepository.findAll()));
    }

    public ResponseEntity<?> saveTransaction(NewTransactionDTO newTransactionDTO) {
       return ResponseEntity.ok(
               new ReadTransactionDTO(
                       transactionRepository.save(
                               new Transaction(newTransactionDTO))));
    }

    private static List<ReadTransactionDTO> toReadTransactionDTO(List<Transaction> transactions){
        return transactions.stream()
                .map(ReadTransactionDTO::new)
                .toList();
    }
}
