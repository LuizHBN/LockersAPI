package com.inertia.lockersapi.domain.facility;

import com.inertia.lockersapi.api.controller.dto.request.facility.NewFacilityDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;
@Table(name = "facility")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Facility {

    @Id
    @GeneratedValue
    private UUID id;
    private String address;
    private String cep;
    private String lat;
    private String lon;


    public Facility(NewFacilityDTO facilityDTO) {
        this.address = facilityDTO.address();
        this.cep =  facilityDTO.cep();
        this.lat = facilityDTO.lat();
        this.lon = facilityDTO.lon();
    }
}
