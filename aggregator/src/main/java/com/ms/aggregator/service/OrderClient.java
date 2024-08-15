/*
 * Copyright (c) 2024.
 *
 * @author Bhagwat Kumar
 */

package com.ms.aggregator.service;

import com.ms.aggregator.dto.Order;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Primary;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * Open Feign client for Order microservice with circuit-breaker service CustomerClientFallback
 */
@Primary
@FeignClient(name = "order-service", url = "${order.service.url}")
public interface OrderClient {
    @GetMapping("/{id}")
    Order getById(@PathVariable("id") Long id);

    @GetMapping
    List<Order> getAll();
}
