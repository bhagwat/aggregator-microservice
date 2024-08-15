
/*
 * Copyright (c) 2024.
 *
 * @author Bhagwat Kumar
 */

package com.ms.aggregator.dto;

/**
 * DTO for Address to be used in Customer DTO
 *
 * @param city    City name
 * @param zipCode Zip code
 */
public record Address(
        String city,
        String zipCode
) {
}
