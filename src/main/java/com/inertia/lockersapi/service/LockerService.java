package com.inertia.lockersapi.service;

import com.inertia.lockersapi.domain.locker.Locker;
import com.inertia.lockersapi.domain.locker.LockerDTO;
import com.inertia.lockersapi.repositories.LockerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LockerService {

    @Autowired
    private LockerRepository lockerRepository;


    public Locker registerLocker(LockerDTO data) {

        Locker newLocker = new Locker();

        newLocker.setAddress(data.address());
        newLocker.setFree(true);

        lockerRepository.save(newLocker);

        return newLocker;
    }
}
