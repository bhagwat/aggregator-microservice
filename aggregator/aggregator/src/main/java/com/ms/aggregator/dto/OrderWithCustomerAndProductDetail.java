package com.ms.aggregator.dto;

import java.util.List;
import java.util.Objects;

public record OrderWithCustomerAndProductDetail(Long id, String couponCode, Customer customer,
                                                List<OrderItemWithProductDetail> items) {

    public static OrderWithCustomerAndProductDetail mergeProductAndCustomer(Order order, Customer customer, List<Product> products) {
        List<OrderItemWithProductDetail> orderItems = order.items().stream()
                .map(item -> products.stream()
                        .filter(p -> Objects.equals(p.id(), item.productId()))
                        .map(product -> new OrderItemWithProductDetail(item.id(), product, item.quantity(), item.price()))
                        .findFirst()
                        .orElse(null)
                ).toList();

        return new OrderWithCustomerAndProductDetail(order.id(), order.couponCode(), customer, orderItems);
    }

}
