package com.example.robottest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception class in case order configuration is invalid or no parts in stock
 */
@ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
public class OrderProcessException extends RuntimeException {
    public OrderProcessException(String message){
        super(message);
    }
}
