/*
 * Copyright (c) 2024.
 *
 * @author Bhagwat Kumar
 */

package com.ms.aggregator.dto;

/**
 * OrderItem DTO
 *
 * @param id
 * @param productId
 * @param quantity
 * @param price
 */
public record OrderItem(
        Long id,
        String productId,
        Integer quantity,
        Double price
) {
}
