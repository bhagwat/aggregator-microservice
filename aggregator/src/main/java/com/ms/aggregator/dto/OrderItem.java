package com.ms.aggregator.dto;

public record OrderItem(Long id, String productId, Integer quantity, Double price) {

}
