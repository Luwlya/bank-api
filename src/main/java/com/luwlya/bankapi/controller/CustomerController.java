package com.luwlya.bankapi.controller;

import com.luwlya.bankapi.dto.CreateCustomerRequest;
import com.luwlya.bankapi.dto.CustomerDto;
import com.luwlya.bankapi.dto.CustomersListDto;
import com.luwlya.bankapi.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

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
        return ResponseEntity.ok().body(customerService.getAllCustomers());
    }

    @GetMapping("/customers/{id}")
    public ResponseEntity<CustomerDto> getCustomer(@PathVariable UUID id){
        CustomerDto dto = customerService.getCustomer(id);
        return ResponseEntity.ok().body(dto);
    }

    @PatchMapping ("/customers/{id}")
    public ResponseEntity<CustomerDto> updateCustomer(@PathVariable String id, @RequestBody CreateCustomerRequest update) {
        CustomerDto dto = customerService.updateCustomer(id, update);
        return ResponseEntity.ok().body(dto);
    }

    @DeleteMapping("/customers/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable String id){
        customerService.deleteCustomer(id);
        return ResponseEntity.noContent().build();
    }
}
