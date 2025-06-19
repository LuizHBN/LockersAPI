package com.inertia.lockersapi.domain.transaction.repository;

import com.inertia.lockersapi.domain.transaction.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TransactionRepository extends JpaRepository<Transaction, UUID> {
}
