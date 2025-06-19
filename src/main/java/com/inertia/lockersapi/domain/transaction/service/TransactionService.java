package com.inertia.lockersapi.domain.transaction.service;

import com.inertia.lockersapi.api.controller.dto.request.transaction.NewTransactionDTO;
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

    public ResponseEntity<List<Transaction>> findAllTransactions() {
        return ResponseEntity.ok(transactionRepository.findAll());
    }

    public ResponseEntity<?> saveTransaction(NewTransactionDTO newTransactionDTO) {
       transactionRepository.saveAndFlush(new Transaction(newTransactionDTO));
       return ResponseEntity.ok(newTransactionDTO);
    }
}
