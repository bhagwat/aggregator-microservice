/*
 * Copyright (c) 2024.
 *
 * @author Bhagwat Kumar
 */

package com.ms.order.errorHandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

/**
 * Global Error Handler
 */
@RestControllerAdvice
public class OrderErrorHandler {
    /**
     * Order not found exception handler
     *
     * @param exception OrderNotFound exception
     * @return Error Map
     */
    @ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity<Map<String, String>> orderNotFound(OrderNotFoundException exception) {
        Map<String, String> response = Map.of("message", String.format("Order with id %d not found", exception.orderId));
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}
