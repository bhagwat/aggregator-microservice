package com.ms.aggregator.controller;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import com.ms.aggregator.dto.AggregateData;
import com.ms.aggregator.dto.Customer;
import com.ms.aggregator.dto.Order;
import com.ms.aggregator.dto.Product;
import com.ms.aggregator.service.CustomerClient;
import com.ms.aggregator.service.OrderClient;
import com.ms.aggregator.service.ProductClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class AggregatorController {
    private final OrderClient orderClient;
    private final ProductClient productClient;
    private final CustomerClient customerClient;

    @Autowired
    public AggregatorController(OrderClient orderClient, ProductClient productClient, CustomerClient customerClient) {
        this.orderClient = orderClient;
        this.productClient = productClient;
        this.customerClient = customerClient;
    }

    @GetMapping("/aggregate/{id}")
    public AggregateData aggregate(@PathVariable String id) throws ExecutionException, InterruptedException {
        CompletableFuture<Order> orderCompletableFuture = CompletableFuture.supplyAsync(() -> orderClient.getById(id));
        Order order = orderCompletableFuture.get();

        CompletableFuture<List<Product>> productListCompletableFuture = CompletableFuture.supplyAsync(() -> {
                    return order.items()
                            .stream()
                            .map(orderItem -> productClient.getById(orderItem.productId()))
                            .toList();
                }
        ).exceptionally(ex -> {
                    log.error("Api 4 threw  exception msg-{} ", ex.getMessage());
                    return order.items()
                            .stream()
                            .map(orderItem -> new Product(orderItem.productId(), "", ""))
                            .toList();
                }
        );
        ;
        CompletableFuture<Customer> customerCompletableFuture = CompletableFuture.supplyAsync(() -> {
            return customerClient.getById(order.customerId());
        }).exceptionally(ex -> {
                    log.error("Api 4 threw  exception msg-{} ", ex.getMessage());
                    return new Customer(order.customerId(), "", null);
                }
        );

        CompletableFuture.allOf(
                productListCompletableFuture,
                customerCompletableFuture
        ).join();

        List<Product> productList = productListCompletableFuture.get();

        Customer customer = customerCompletableFuture.get();

        return new AggregateData(order, productList, customer);
    }
}
