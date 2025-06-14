package com.inertia.lockersapi.domain.locker;

import com.inertia.lockersapi.api.controller.dto.request.locker.NewLockerDTO;
import jakarta.persistence.*;
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

    @Column(name = "facility_id")
    private UUID facilityId;

    private String address;

    private boolean isFree;

    public Locker(NewLockerDTO lockerDTO) {
        this.isFree = lockerDTO.isFree();
        this.facilityId = lockerDTO.facilityID();
    }


}
