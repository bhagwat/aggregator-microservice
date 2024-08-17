/*
 * Copyright (c) 2024.
 *
 * @author Bhagwat Kumar
 */

package com.ms.order.errorHandler;

import lombok.NoArgsConstructor;

/**
 * Order not found error class
 */
@NoArgsConstructor
public class OrderNotFoundException extends RuntimeException {
    Long orderId;

    public OrderNotFoundException(Long orderId) {
        super();
        this.orderId = orderId;
    }
}
