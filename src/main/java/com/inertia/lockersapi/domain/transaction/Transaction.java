package com.inertia.lockersapi.domain.transaction;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.inertia.lockersapi.api.controller.dto.request.transaction.NewTransactionDTO;
import com.inertia.lockersapi.domain.user.User;
import jakarta.persistence.*;
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
    @GeneratedValue
    private UUID id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
    @Enumerated(EnumType.STRING)
    private TransactionType type;
    private boolean validated;
    private double amount;

    public Transaction(NewTransactionDTO newTransactionDTO) {
        this.user = new User();
        this.user.setId(newTransactionDTO.userId());
        this.validated = newTransactionDTO.validated();
        this.amount = newTransactionDTO.amount();
        this.type = newTransactionDTO.type();
    }
}
