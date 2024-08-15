/*
 * Copyright (c) 2024.
 *
 * @author Bhagwat Kumar
 */

package com.ms.aggregator.controller;

import com.ms.aggregator.dto.Order;
import com.ms.aggregator.dto.OrderWithCustomerAndProductDetail;
import com.ms.aggregator.service.AggregatorService;
import com.ms.aggregator.service.OrderClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Order related GraphQL endpoints
 */
@Controller
public class OrderController {
    private final OrderClient orderClient;
    private final AggregatorService aggregatorService;

    @Autowired
    public OrderController(OrderClient orderClient, AggregatorService aggregatorService) {
        this.orderClient = orderClient;
        this.aggregatorService = aggregatorService;
    }

    /**
     * Graphql Query handler for getting all the orders
     *
     * @return Returns list of orders
     * @throws ExecutionException   ExecutionException
     * @throws InterruptedException InterruptedException
     */
    @QueryMapping
    public List<Order> orders() throws ExecutionException, InterruptedException {
        return orderClient.getAll();
    }

    /**
     * Graphql Query handler for getting order details by ID
     *
     * @param id Order ID
     * @return OrderWithCustomerAndProductDetail instance
     * @throws ExecutionException   ExecutionException
     * @throws InterruptedException InterruptedException
     */
    @QueryMapping
    public OrderWithCustomerAndProductDetail orderById(@Argument Long id)
            throws ExecutionException, InterruptedException {
        return aggregatorService.getOrderDetails(id);
    }
}
