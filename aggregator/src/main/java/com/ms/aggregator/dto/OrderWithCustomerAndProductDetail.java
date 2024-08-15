/*
 * Copyright (c) 2024.
 *
 * @author Bhagwat Kumar
 */

package com.ms.aggregator.dto;

import java.util.List;
import java.util.Objects;

/**
 * OrderWithCustomerAndProductDetail DTO
 *
 * @param id
 * @param couponCode
 * @param customer
 * @param items
 */
public record OrderWithCustomerAndProductDetail(
        Long id,
        String couponCode,
        Customer customer,
        List<OrderItemWithProductDetail> items
) {
    /**
     * OrderWithCustomerAndProductDetail instance form merger of Order, customer and products
     *
     * @param order    Order
     * @param customer Customer
     * @param products Products
     * @return OrderWithCustomerAndProductDetail
     */
    public static OrderWithCustomerAndProductDetail fromOrderCustomerAndProduct(Order order, Customer customer, List<Product> products) {
        List<OrderItemWithProductDetail> orderItems = order.items().stream()
                .map(item -> products.stream()
                        .filter(p -> Objects.equals(p.id(), item.productId()))
                        .map(product -> fromOrderItemAndProduct(item, product))
                        .findFirst()
                        .orElse(null)
                ).toList();

        return new OrderWithCustomerAndProductDetail(order.id(), order.couponCode(), customer, orderItems);
    }

    /**
     * OrderItemWithProductDetail instance from orderItem and product
     *
     * @param item    OrderItem instance
     * @param product Product instance
     * @return OrderItemWithProductDetail
     */
    public static OrderItemWithProductDetail fromOrderItemAndProduct(OrderItem item, Product product) {
        return new OrderItemWithProductDetail(item.id(), product, item.quantity(), item.price());
    }

}
