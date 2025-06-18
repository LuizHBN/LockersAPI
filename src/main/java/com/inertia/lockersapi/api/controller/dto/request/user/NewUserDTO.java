package com.inertia.lockersapi.api.controller.dto.request.user;

public record NewUserDTO(
        String name,
        String lastName,
        String cpf,
        String phone,
        String password,
        String email
) {
}
