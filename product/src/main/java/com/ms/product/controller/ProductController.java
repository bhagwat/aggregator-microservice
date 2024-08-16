/*
 * Copyright (c) 2024.
 *
 * @author Bhagwat Kumar
 */

package com.ms.product.controller;

import com.ms.product.dto.ProductDto;
import com.ms.product.errorHanlder.ProductNotFoundException;
import com.ms.product.repository.ProductRepository;
import com.ms.product.service.CategoryClient;
import com.ms.product.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductRepository productRepository, ProductService productService, CategoryClient categoryClient) {
        this.productService = productService;
    }

    @GetMapping("/{id}")
    Mono<ProductDto> getProductById(@PathVariable String id) {
        log.info("findById:: {}", id);
        return productService.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
    }

    @GetMapping()
    Mono<List<ProductDto>> getProductsByIds(@RequestParam List<String> ids) {
        log.info("findByIds:: {}", ids);
        return productService.findAllByIds(ids);
    }

    @GetMapping("/all")
    Mono<List<ProductDto>> getAllProducts() {
        log.info("findAll");
        return productService.findAll();
    }
}
