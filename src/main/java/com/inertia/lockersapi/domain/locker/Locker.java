package com.inertia.lockersapi.domain.locker;

import com.inertia.lockersapi.api.controller.dto.request.NewLockerDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
