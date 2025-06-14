package com.inertia.lockersapi.api.controller.dto.request.transaction;

import com.inertia.lockersapi.domain.transaction.TransactionType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;

import java.util.UUID;

public record NewTransactionDTO(
        UUID id,
        UUID userId,
        TransactionType type,
        boolean validated,
        double amount
) {
}
