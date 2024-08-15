/*
 * Copyright (c) 2024.
 *
 * @author Bhagwat Kumar
 */

package com.ms.aggregator.dto;

/**
 * OrderItemWithProductDetail DTO
 *
 * @param id
 * @param product
 * @param quantity
 * @param price
 */
public record OrderItemWithProductDetail(
        Long id,
        Product product,
        Integer quantity,
        Double price) {
}