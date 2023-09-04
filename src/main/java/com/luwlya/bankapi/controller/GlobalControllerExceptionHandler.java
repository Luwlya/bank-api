package com.luwlya.bankapi.controller;

import com.luwlya.bankapi.dto.Problem;
import com.luwlya.bankapi.exception.CustomerNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalControllerExceptionHandler {
    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<Problem> handleCustomerNotFound(CustomerNotFoundException exception){
       return ResponseEntity.status(404).body(new Problem(404, "NOT_FOUND", exception.getMessage()));
    }
}
