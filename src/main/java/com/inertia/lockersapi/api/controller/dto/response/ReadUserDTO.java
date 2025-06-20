package com.inertia.lockersapi.api.controller.dto.response;

import com.inertia.lockersapi.domain.user.User;

import java.util.UUID;

public record ReadUserDTO(UUID id, String name, String lastName, String email, String phone, String cpf ) {

    public ReadUserDTO(User user ) {
        this(user.getId(), user.getName(), user.getLastName(), user.getEmail(), user.getPhone(), user.getCpf());
    }
}

