package com.inertia.lockersapi.api.controller.dto.request.transaction;

import com.inertia.lockersapi.domain.transaction.TransactionType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record NewTransactionDTO(

        @NotNull
        UUID userId,
        @NotNull
        TransactionType type,
        @NotNull
        boolean validated,
        @NotNull
        double amount,
        @NotNull
        UUID rentRequestId
) {
}
