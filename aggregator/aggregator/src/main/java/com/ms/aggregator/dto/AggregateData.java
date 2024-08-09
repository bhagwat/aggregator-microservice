package com.ms.aggregator.dto;

import java.util.List;

public record AggregateData(Order order, List<Product> products, Customer customer) {
}
