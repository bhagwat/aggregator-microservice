package com.ms.aggregator.service;

import com.ms.aggregator.dto.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Primary;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Primary
@FeignClient(name = "product-service", url = "${product.service.url}", fallback = ProductClientFallback.class)
public interface ProductClient {
    @GetMapping("/product/{id}")
    Product getById(@PathVariable("id") String id);

    @GetMapping("/product")
    List<Product> getAll();
}

