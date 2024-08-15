/*
 * Copyright (c) 2024.
 *
 * @author Bhagwat Kumar
 */

package com.ms.aggregator.service;

import com.ms.aggregator.dto.Customer;
import com.ms.aggregator.dto.OrderItem;
import com.ms.aggregator.dto.OrderWithCustomerAndProductDetail;
import com.ms.aggregator.dto.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * Aggregator service using CompletableFuture to get data from multiple microservices and merging to created a consolidated response
 */
@Service
public class AggregatorService {
    private final OrderClient orderClient;
    private final ProductClient productClient;
    private final CustomerClient customerClient;

    @Autowired
    public AggregatorService(OrderClient orderClient, ProductClient productClient, CustomerClient customerClient) {
        this.orderClient = orderClient;
        this.productClient = productClient;
        this.customerClient = customerClient;
    }

    /**
     * Get order details with customer and product details in the order items populated from other microservices
     *
     * @param id Order ID
     * @return OrderWithCustomerAndProductDetail instance
     * @throws ExecutionException   ExecutionException
     * @throws InterruptedException InterruptedException
     */
    public OrderWithCustomerAndProductDetail getOrderDetails(Long id) throws ExecutionException, InterruptedException {
        // Get order future using order microservice
        var orderFuture = CompletableFuture.supplyAsync(() -> orderClient.getById(id));

        // Get customer future using customer id order
        CompletableFuture<Customer> customerFuture = orderFuture.thenCompose(order ->
                CompletableFuture.supplyAsync(() -> customerClient.getById(order.customerId()))
        );

        // Get products future using orderItems from order
        CompletableFuture<List<Product>> productsFuture = orderFuture.thenCompose(order ->
                CompletableFuture.supplyAsync(() -> order.items()
                        .stream()
                        .map(OrderItem::productId)
                        .map(productId -> CompletableFuture.supplyAsync(() ->
                                productClient.getById(productId))
                        )
                        .toList()
                ).thenCompose(futures ->
                        CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]))
                                .thenApply(_ -> futures.stream()
                                        .map(CompletableFuture::join)
                                        .toList()
                                )
                )
        );

        //  Combine all the fetched details
        CompletableFuture<Void> allOf = CompletableFuture.allOf(orderFuture, customerFuture, productsFuture);

        // Wait for all futures to complete
        allOf.get();

        return OrderWithCustomerAndProductDetail.fromOrderCustomerAndProduct(
                orderFuture.get(),
                customerFuture.get(),
                productsFuture.get()
        );
    }
}
