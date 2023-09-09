package com.luwlya.bankapi.dto.manager;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

public record UpdateManagerRequest(
        @Size(min = 1, max = 50)
        String firstName,
        @Size(min = 1, max = 50)
        String lastName,
        @Email
        String email,
        @Size(min = 1, max = 50)
        String password
) {
}
