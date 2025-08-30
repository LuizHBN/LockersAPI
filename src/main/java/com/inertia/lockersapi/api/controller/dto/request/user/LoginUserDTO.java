package com.inertia.lockersapi.api.controller.dto.request.user;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

public record LoginUserDTO(
        @Email
        String email,
        @NotNull
        String password
) {
}
