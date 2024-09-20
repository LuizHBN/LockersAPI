package com.inertia.lockersapi.domain.locker;

import com.inertia.lockersapi.api.controller.dto.request.NewLockerCheckOutDTO;
import com.inertia.lockersapi.api.controller.dto.request.NewLockerDTO;
import com.inertia.lockersapi.api.controller.dto.request.NewRentRequestDTO;
import com.inertia.lockersapi.domain.locker.repository.LockerRepository;
import com.inertia.lockersapi.domain.rentRequest.RentRequest;
import com.inertia.lockersapi.domain.rentRequest.repository.RentRequestRepository;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Table(name = "locker")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Locker {

    @Id
    @GeneratedValue
    private UUID id;

    private String address;

    private boolean isFree;

    public Locker(NewLockerDTO lockerDTO) {
        this.address = lockerDTO.address();
        this.isFree = lockerDTO.isFree();
    }


}
