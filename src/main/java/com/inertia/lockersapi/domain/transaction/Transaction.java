package com.inertia.lockersapi.domain.transaction;

import com.inertia.lockersapi.api.controller.dto.request.transaction.NewTransactionDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
public class Transaction {

    @Id
    private UUID id;
    private UUID userId;
    @Enumerated(EnumType.STRING)
    private TransactionType type;
    private boolean validated;
    private double amount;

    public Transaction(NewTransactionDTO newTransactionDTO) {
        this.userId = newTransactionDTO.userId();
        this.validated = newTransactionDTO.validated();
        this.amount = newTransactionDTO.amount();
        this.type = newTransactionDTO.type();
    }
}
