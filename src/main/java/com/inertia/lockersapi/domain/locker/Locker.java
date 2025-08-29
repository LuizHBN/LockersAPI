package com.inertia.lockersapi.domain.locker;

import com.inertia.lockersapi.api.controller.dto.request.locker.NewLockerDTO;
import com.inertia.lockersapi.domain.facility.Facility;
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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "facility_id")
    private Facility facility;
    private boolean isFree;
    private String alias;
    private String lockerModel;

    public Locker(NewLockerDTO lockerDTO) {
        this.isFree = lockerDTO.isFree();
        this.lockerModel = lockerDTO.lockerModel();
        this.facility = new Facility();
        this.facility.setId(lockerDTO.facilityID());
        this.alias = lockerDTO.alias();

    }


}
