/*
 * Copyright (c) 2024.
 *
 * @author Bhagwat Kumar
 */

package com.ms.aggregator.dto;

/**
 * Product DTO
 *
 * @param id
 * @param name
 * @param description
 */
public record Product(
        String id,
        String name,
        String description
) {
}
