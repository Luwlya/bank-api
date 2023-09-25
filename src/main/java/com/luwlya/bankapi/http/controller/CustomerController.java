package com.luwlya.bankapi.http.controller;

import com.luwlya.bankapi.dto.customer.CreateCustomerRequest;
import com.luwlya.bankapi.dto.customer.CustomerDto;
import com.luwlya.bankapi.dto.customer.CustomersListDto;
import com.luwlya.bankapi.dto.customer.UpdateCustomerRequest;
import com.luwlya.bankapi.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@Validated
@Secured("ROLE_ADMIN")
public class CustomerController {
    private CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/customers")
    public ResponseEntity<CustomerDto> createCustomer(@RequestBody @Valid CreateCustomerRequest request) {
        CustomerDto customer = customerService.createCustomer(request);
        return ResponseEntity.ok().body(customer);
    }

    @GetMapping("/customers")
    public ResponseEntity<CustomersListDto> getAllCustomers() {
        return ResponseEntity.ok().body(customerService.getAllCustomers());
    }

    @GetMapping("/customers/{id}")
    public ResponseEntity<CustomerDto> getCustomer(@PathVariable UUID id) {
        CustomerDto dto = customerService.getCustomer(id);
        return ResponseEntity.ok().body(dto);
    }

    @PatchMapping("/customers/{id}")
    public ResponseEntity<CustomerDto> updateCustomer(@PathVariable UUID id, @RequestBody @Valid UpdateCustomerRequest update) {
        CustomerDto dto = customerService.updateCustomer(id, update);
        return ResponseEntity.ok().body(dto);
    }

    @DeleteMapping("/customers/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable UUID id) {
        customerService.deleteCustomer(id);
        return ResponseEntity.noContent().build();
    }
}
