package com.inertia.lockersapi.api.controller.dto.request.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record NewUserDTO(
        @NotNull
        @NotBlank
        String name,
        @NotNull
        @NotBlank
        String lastName,
        @NotNull
        @NotBlank
        String cpf,
        @NotNull
        @NotBlank
        String phone,
        @NotNull
        @NotBlank
        String password,
        @NotNull
        @NotBlank
        String email
) {
}
