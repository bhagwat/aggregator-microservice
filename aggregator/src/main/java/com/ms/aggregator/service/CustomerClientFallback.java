/*
 * Copyright (c) 2024.
 *
 * @author Bhagwat Kumar
 */

package com.ms.aggregator.service;

import com.ms.aggregator.dto.Customer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Fallback service for CustomerClient microservice
 */
@Slf4j
@Component
public class CustomerClientFallback implements CustomerClient {
    @Override
    public Customer getById(Long id) {
        log.error("Trigger CustomerClientFallback.getById");
        return new Customer(id, "", null);
    }

    @Override
    public List<Customer> getAll() {
        log.error("Trigger CustomerClientFallback.getAll");
        return List.of();
    }
}
