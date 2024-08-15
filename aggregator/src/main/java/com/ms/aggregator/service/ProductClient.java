/*
 * Copyright (c) 2024.
 *
 * @author Bhagwat Kumar
 */

package com.ms.aggregator.service;

import com.ms.aggregator.dto.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Primary;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * Open Feign client for Product microservice with circuit-breaker service ProductClientFallback
 */
@Primary
@FeignClient(name = "product-service", url = "${product.service.url}", fallback = ProductClientFallback.class)
public interface ProductClient {
    @GetMapping("/{id}")
    Product getById(@PathVariable("id") String id);

    @GetMapping
    List<Product> getAll();
}

