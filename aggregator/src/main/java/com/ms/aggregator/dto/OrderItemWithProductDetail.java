package com.ms.aggregator.dto;

public record OrderItemWithProductDetail(Long id, Product product, Integer quantity, Double price) {

}