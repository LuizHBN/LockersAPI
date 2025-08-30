package com.inertia.lockersapi.api.controller.dto.response;

import com.inertia.lockersapi.domain.user.User;

import java.util.UUID;

public record ReadUserLoginDTO(
        UUID userId
) {
    public  ReadUserLoginDTO(User user) {
        this(user.getId());
    }
}
