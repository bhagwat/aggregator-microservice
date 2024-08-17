/*
 * Copyright (c) 2024.
 *
 * @author Bhagwat Kumar
 */

package com.ms.product.errorHanlder;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ProductErrorHandler {
    @ExceptionHandler(ProductNotFoundException.class)
    ResponseEntity<Object> handleProductNotFound(ProductNotFoundException exception) {
        Map<String, Object> resp = new HashMap<>();
        resp.put("message", exception.getMessage());
        return new ResponseEntity<>(resp, HttpStatus.NOT_FOUND);
    }
}
