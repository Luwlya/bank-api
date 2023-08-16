package com.luwlya.bankapi.controller;

import com.luwlya.bankapi.dto.CreateCustomerRequest;
import com.luwlya.bankapi.dto.CustomerDto;
import com.luwlya.bankapi.dto.CustomersListDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.OffsetDateTime;
import java.util.List;

@RestController
public class CustomerController {
    @PostMapping("/customers")
    public ResponseEntity<CustomerDto> createCustomer(@RequestBody CreateCustomerRequest request) {
        CustomerDto dto = new CustomerDto("id1",
                request.firstName(),
                request.lastName(),
                request.email(),
                request.address(),
                request.phone(),
                OffsetDateTime.now(),
                OffsetDateTime.now());
        System.out.println(request);
        return ResponseEntity.ok().body(dto);
    }

    @GetMapping("/customers")
    public ResponseEntity<CustomersListDto> getAllCustomers() {
        return ResponseEntity.ok().body(new CustomersListDto(List.of()));
    }

    
}
