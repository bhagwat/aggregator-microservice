package com.ms.aggregator.dto;

import java.util.List;

public record Order(Long id, String couponCode, Long customerId, List<OrderItem> items) {
}
