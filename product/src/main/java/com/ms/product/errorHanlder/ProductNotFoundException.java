package com.ms.product.errorHanlder;

public class ProductNotFoundException extends RuntimeException {
    ProductNotFoundException() {
        super();
    }

    public ProductNotFoundException(String productId) {
        super(String.format("Product not found with ID: %s", productId));
    }
}
