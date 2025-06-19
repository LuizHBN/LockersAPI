package com.inertia.lockersapi.api.controller;

import com.inertia.lockersapi.api.controller.dto.request.transaction.NewTransactionDTO;
import com.inertia.lockersapi.domain.transaction.Transaction;
import com.inertia.lockersapi.domain.transaction.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/transaction")
public class TransactionController {
    @Autowired
    private TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping
    public ResponseEntity<?> getTransactions(){
        return ResponseEntity.ok(this.transactionService.findAllTransactions());
    }

    @PostMapping
    public ResponseEntity<?> addTransaction(@RequestBody NewTransactionDTO newTransactionDTO){
        this.transactionService.saveTransaction(newTransactionDTO);
        return ResponseEntity.ok(newTransactionDTO);
    }




}
