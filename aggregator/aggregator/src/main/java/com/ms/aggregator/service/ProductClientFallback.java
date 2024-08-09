package com.ms.aggregator.service;

import java.util.List;

import com.ms.aggregator.dto.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ProductClientFallback implements ProductClient {
    @Override
    public Product getById(String id) {
        log.error("Trigger ProductClientFallback.getById");
        return new Product(id, "", "");
    }

    @Override
    public List<Product> getAll() {
        log.error("Trigger ProductClientFallback.getAll");
        return List.of();
    }
}
