package com.luwlya.bankapi.http;

import com.luwlya.bankapi.dto.Problem;
import com.luwlya.bankapi.exception.CustomerNotFoundException;
import com.luwlya.bankapi.exception.InsufficientBalanceException;
import com.luwlya.bankapi.exception.ManagerNotFoundException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalControllerExceptionHandler {
    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<Problem> handleCustomerNotFound(CustomerNotFoundException exception){
       return ResponseEntity.status(404).body(new Problem(404, "NOT_FOUND", exception.getMessage()));
    }

    @ExceptionHandler(ConstraintViolationException.class)
    ResponseEntity<Problem> handleConstraintViolationException(ConstraintViolationException exception) {
        return ResponseEntity.status(400).body(new Problem(400, "BAD_REQUEST", exception.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    ResponseEntity<Problem> handleConstraintViolationException(MethodArgumentNotValidException exception) {
        return ResponseEntity.status(400).body(new Problem(400, "BAD_REQUEST", exception.getMessage()));
    }

    @ExceptionHandler(ManagerNotFoundException.class)
    public ResponseEntity<Problem> handleManagerNotFound(ManagerNotFoundException exception){
        return ResponseEntity.status(404).body(new Problem(404, "NOT_FOUND", exception.getMessage()));
    }

    @ExceptionHandler(InsufficientBalanceException.class)
    public ResponseEntity<Problem> handleInsufficientBalance(InsufficientBalanceException exception){
        return ResponseEntity.status(400).body(new Problem(400, "BAD_REQUEST", exception.getMessage()));
    }
}
