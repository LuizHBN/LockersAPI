package com.inertia.lockersapi.domain.locker;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Locker {
    private Long id;
    private String address;
    private boolean isFree;

}
