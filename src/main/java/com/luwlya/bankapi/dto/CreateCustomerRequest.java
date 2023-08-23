package com.luwlya.bankapi.dto;


public record CreateCustomerRequest(
        String firstName,
        String lastName,
        String email,
        String address,
        String phone
) {
}
