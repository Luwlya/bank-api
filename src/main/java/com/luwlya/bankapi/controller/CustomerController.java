package com.luwlya.bankapi.controller;

import com.luwlya.bankapi.dto.CreateCustomerRequest;
import com.luwlya.bankapi.dto.CustomerDto;
import com.luwlya.bankapi.dto.CustomersListDto;
import com.luwlya.bankapi.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.OffsetDateTime;
import java.util.List;

@RestController
public class CustomerController {
    private CustomerService customerService;
    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/customers")
    public ResponseEntity<CustomerDto> createCustomer(@RequestBody CreateCustomerRequest request) {
        CustomerDto customer = customerService.createCustomer(request);
        return ResponseEntity.ok().body(customer);
    }

    @GetMapping("/customers")
    public ResponseEntity<CustomersListDto> getAllCustomers() {
        return ResponseEntity.ok().body(new CustomersListDto(List.of()));
    }

    @GetMapping("/customers/{id}")
    public ResponseEntity<CustomerDto> getCustomer(@PathVariable String id){
        CustomerDto dto = new CustomerDto(id,
                "Ostap",
                "Vyshnya",
                "ovyshnya@ukr.com",
                "Kyiv",
                "+380670000001",
                OffsetDateTime.now(),
                OffsetDateTime.now());
        return ResponseEntity.ok().body(dto);
    }

    @PatchMapping ("/customers/{id}")
    public ResponseEntity<CustomerDto> updateCustomer(@PathVariable String id, @RequestBody CreateCustomerRequest update) {
        CustomerDto dto = new CustomerDto(id,
                update.firstName(),
                update.lastName(),
                update.email(),
                update.address(),
                update.phone(),
                OffsetDateTime.now(),
                OffsetDateTime.now());
        return ResponseEntity.ok().body(dto);
    }

    @DeleteMapping("/customers/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable String id){
        System.out.println("Customer " + id + " has been deleted");
        return ResponseEntity.noContent().build();
    }
}
