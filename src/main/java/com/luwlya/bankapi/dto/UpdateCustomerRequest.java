package com.luwlya.bankapi.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UpdateCustomerRequest(
        @Size(min = 1, max = 50)
        String firstName,
        @Size(min = 1, max = 50)
        String lastName,
        @Email
        String email,                                        
        @Size(min = 1, max = 50)
        String address,
        @Pattern(regexp = "(\\d+){12}")
        String phone
) {
}
