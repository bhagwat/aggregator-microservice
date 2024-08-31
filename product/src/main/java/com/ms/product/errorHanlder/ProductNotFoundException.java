/*
 * Copyright (c) 2024.
 *
 * @author Bhagwat Kumar
 */

package com.ms.product.errorHanlder;

public class ProductNotFoundException extends RuntimeException {

    public ProductNotFoundException(String productId) {
        super(String.format("Product not found with ID: %s", productId));
    }
}
