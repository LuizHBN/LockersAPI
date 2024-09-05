package com.inertia.lockersapi.domain.locker;

import com.inertia.lockersapi.api.controller.dto.request.NewRentRequestDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
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

    @Column(name = "lockerId", nullable = false)
    private UUID locker_id;

    @Column(name = "userId", nullable = false)
    private Integer user_id;

    @Column(name = "rent_start_date", nullable = false)
    private Date rentStartDate;

    @Column(name = "rent_finish_date")
    private Date rentFinishDate;

    public RentRequest(NewRentRequestDTO requestDTO){
        this.locker_id = requestDTO.lockerId();
        this.user_id = requestDTO.userId();
        this.rentStartDate = requestDTO.rentStartDate();
        this.rentFinishDate = requestDTO.rentFinishDate();
    }
}
