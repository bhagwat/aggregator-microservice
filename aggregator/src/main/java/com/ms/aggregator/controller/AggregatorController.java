/*
 * Copyright (c) 2024.
 *
 * @author Bhagwat Kumar
 */

package com.ms.aggregator.controller;

import com.ms.aggregator.dto.OrderWithCustomerAndProductDetail;
import com.ms.aggregator.service.AggregatorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;

/**
 * Aggregator API
 */
@Slf4j
@RestController
public class AggregatorController {
    private final AggregatorService aggregatorService;

    @Autowired
    public AggregatorController(AggregatorService aggregatorService) {
        this.aggregatorService = aggregatorService;
    }

    /**
     * Get Order details by ID
     *
     * @param id Order ID
     * @return OrderWithCustomerAndProductDetail
     * @throws ExecutionException   ExecutionException
     * @throws InterruptedException InterruptedException
     */
    @GetMapping("/aggregate/{id}")
    public OrderWithCustomerAndProductDetail aggregate(@PathVariable Long id)
            throws ExecutionException, InterruptedException {
        return aggregatorService.getOrderDetails(id);
    }
}
