package com.ms.aggregator.service;

import java.util.List;

import com.ms.aggregator.dto.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Primary;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Primary
@FeignClient(name = "product-service", url = "${product.service.url}", fallback = ProductClientFallback.class)
public interface ProductClient {
    @GetMapping("/product/{id}")
    Product getById(@PathVariable("id") String id);

    @GetMapping("/product")
    List<Product> getAll();
}

