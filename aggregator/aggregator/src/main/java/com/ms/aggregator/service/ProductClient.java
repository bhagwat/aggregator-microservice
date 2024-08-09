package com.ms.aggregator.service;

import java.util.List;

import com.ms.aggregator.dto.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "product-service", url = "${product.service.url}")
public interface ProductClient {
    @GetMapping("/product/{id}")
    Product getById(@PathVariable("id") String id);

    @GetMapping("/product")
    List<Product> getAll();
}
