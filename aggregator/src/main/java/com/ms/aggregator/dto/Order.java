/*
 * Copyright (c) 2024.
 *
 * @author Bhagwat Kumar
 */

package com.ms.aggregator.dto;

import java.util.List;

/**
 * Order DTO
 *
 * @param id
 * @param couponCode
 * @param customerId
 * @param items
 */
public record Order(
        Long id,
        String couponCode,
        Long customerId,
        List<OrderItem> items
) {
}
