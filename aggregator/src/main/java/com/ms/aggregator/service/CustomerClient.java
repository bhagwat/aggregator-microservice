/*
 * Copyright (c) 2024.
 *
 * @author Bhagwat Kumar
 */

package com.ms.aggregator.service;

import com.ms.aggregator.dto.Customer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Primary;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * Open Feign client for Customer microservice with circuit-breaker service CustomerClientFallback
 */
@Primary
@FeignClient(name = "customer-service", url = "${customer.service.url}", fallback = CustomerClientFallback.class)
public interface CustomerClient {
    @GetMapping("/{id}")
    Customer getById(@PathVariable("id") Long id);

    @GetMapping
    List<Customer> getAll();
}
