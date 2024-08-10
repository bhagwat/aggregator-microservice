package com.ms.aggregator.service;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import com.ms.aggregator.dto.Customer;
import com.ms.aggregator.dto.OrderItem;
import com.ms.aggregator.dto.OrderWithCustomerAndProductDetail;
import com.ms.aggregator.dto.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public OrderWithCustomerAndProductDetail getOrderDetails(Long id) throws ExecutionException, InterruptedException {
        var orderFuture = CompletableFuture.supplyAsync(() -> orderClient.getById(id));

        CompletableFuture<Customer> customerFuture = orderFuture.thenCompose(order ->
                CompletableFuture.supplyAsync(() -> customerClient.getById(order.customerId()))
        );

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

        // Step 4: Combine all the fetched details
        CompletableFuture<Void> allOf = CompletableFuture.allOf(orderFuture, customerFuture, productsFuture);

        // Wait for all futures to complete
        allOf.get();

        return OrderWithCustomerAndProductDetail.mergeProductAndCustomer(orderFuture.get(), customerFuture.get(), productsFuture.get());
    }
}
