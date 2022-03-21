package com.example.robottest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception class in case order is invalid
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class OrderException extends RuntimeException{

    public OrderException(String message){
        super(message);
    }
}
