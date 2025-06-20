package com.inertia.lockersapi.api.controller.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.inertia.lockersapi.domain.transaction.Transaction;
import com.inertia.lockersapi.domain.transaction.TransactionType;
import com.inertia.lockersapi.domain.user.User;
import jakarta.persistence.*;

import java.util.UUID;

public record ReadTransactionDTO(UUID id, UUID userId,UUID rentRequestId, TransactionType type, boolean validated, double amount) {

    public ReadTransactionDTO(Transaction transaction) {
        this(transaction.getId(), transaction.getUser().getId(),transaction.getRentRequest().getId(), transaction.getType(), transaction.isValidated(), transaction.getAmount());
    }
}

