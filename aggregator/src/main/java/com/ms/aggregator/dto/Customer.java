/*
 * Copyright (c) 2024.
 *
 * @author Bhagwat Kumar
 */

package com.ms.aggregator.dto;

/**
 * Customer DTO
 *
 * @param id
 * @param name
 * @param address
 */
public record Customer(
        Long id,
        String name,
        Address address
) {
}
