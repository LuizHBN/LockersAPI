package com.inertia.lockersapi.domain.locker;

public enum LockerModel {
    SMALL, MEDIUM,LARGE, EXTRA_LARGE;

    public static LockerModel fromString(String value) {
        if (value == null) {
            throw new IllegalArgumentException("Valor nulo não é permitido.");
        }

        try {
            return LockerModel.valueOf(value.toUpperCase().replace(" ", "_"));
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Modelo inválido: " + value);
        }
    }
}
