package com.inertia.lockersapi.service;

import com.inertia.lockersapi.domain.locker.Locker;
import com.inertia.lockersapi.domain.locker.RentLocker;
import com.inertia.lockersapi.domain.locker.RentRequestLockerDTO;
import com.inertia.lockersapi.repositories.LockerRepository;
import com.inertia.lockersapi.repositories.RentLockerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class RentLockerService {

    @Autowired
    private RentLockerRepository rentLockerRepository;

    @Autowired
    private LockerRepository lockerRepository;

    public RentLocker rentLocker(RentRequestLockerDTO data) {
        Locker locker =  lockerRepository.findById(data.locker_id())
                .orElseThrow(() -> new IllegalArgumentException("Locker não encontrado."));




        if (locker.getId().equals(data.locker_id()) && !locker.isFree()) {
            throw new IllegalArgumentException("Locker está em uso!");
        }


        RentLocker newRentLocker = new RentLocker();


        newRentLocker.setLocker_id(data.locker_id());
        newRentLocker.setUser_id(data.user_id());
        newRentLocker.setRent_start_date(new Date());



        // TODO: Se achar o uuid do locker e ele estiver livre, fazer o update na tabela locker para is_free False

        rentLockerRepository.save(newRentLocker);

        return newRentLocker;
    }
}
