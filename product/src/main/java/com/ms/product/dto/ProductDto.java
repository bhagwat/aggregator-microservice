/*
 * Copyright (c) 2024.
 *
 * @author Bhagwat Kumar
 */

package com.ms.product.dto;

import com.ms.product.entity.Product;

public record ProductDto(String id, String name, String description, CategoryDto category) {

    public static ProductDto from(Product product, CategoryDto category) {
        return new ProductDto(
                product.getId(),
                product.getName(),
                product.getDescription(),
                category);
    }
}
