package com.luwlya.bankapi.controller;

import com.luwlya.bankapi.dto.CreateCustomerAccountRequest;
import com.luwlya.bankapi.dto.CustomerAccountDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.OffsetDateTime;

@RestController
public class CustomerAccountController {
    @PostMapping("/customer-accounts")
    public ResponseEntity<CustomerAccountDto> createCustomerAccount(@RequestBody CreateCustomerAccountRequest request){
        CustomerAccountDto customerAccount = new CustomerAccountDto("id1",
                "customerId12",
                "name",
                request.balance(),
                request.currency(),
                OffsetDateTime.now(),
                OffsetDateTime.now());
        return ResponseEntity.ok().body(customerAccount);
        
    }

}
