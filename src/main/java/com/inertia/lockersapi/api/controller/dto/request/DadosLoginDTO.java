package com.inertia.lockersapi.api.controller.dto.request;

import jakarta.validation.constraints.NotBlank;

public record DadosLoginDTO(
        @NotBlank String email,
        @NotBlank String password
) {
}
