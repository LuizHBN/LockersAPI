package com.inertia.lockersapi.domain.rentRequest;

import com.inertia.lockersapi.api.controller.dto.request.rentRequest.NewRentRequestDTO;
import com.inertia.lockersapi.domain.locker.Locker;
import com.inertia.lockersapi.domain.transaction.Transaction;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.Random;
import java.util.UUID;

@Table(name = "rent_locker")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RentRequest {

    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "locker_id")
    private Locker locker;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "transaction_id")
    private Transaction transaction;

    @Column(name = "userId", nullable = false)
    private Integer user_id;

    @Column(name = "rent_start_date", nullable = false)
    private Date rentStartDate;

    @Column(name = "rent_finish_date")
    private Date rentFinishDate;

    @Column
    private UUID openingKey;

    public RentRequest(NewRentRequestDTO requestDTO){
        this.transaction = new Transaction();
        this.transaction.setId(requestDTO.transactionId());
        this.user_id = requestDTO.userId();
        this.rentStartDate = requestDTO.rentStartDate();
        this.rentFinishDate = requestDTO.rentFinishDate();
        this.openingKey = UUID.randomUUID();
    }
}
