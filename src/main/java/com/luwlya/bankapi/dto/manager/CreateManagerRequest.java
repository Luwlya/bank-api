package com.luwlya.bankapi.dto.manager;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record CreateManagerRequest(
        @NotBlank
        String firstName,
        @NotBlank
        String lastName,
        @Email
        String email,
        @NotBlank
        String password
) {
}

