/*
 * Copyright (c) 2024.
 *
 * @author Bhagwat Kumar
 */

package com.ms.product.service;

import com.ms.product.dto.CategoryDto;
import com.ms.product.dto.ProductDto;
import com.ms.product.entity.Product;
import com.ms.product.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

@Slf4j
@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryClient categoryClient;

    public ProductService(ProductRepository productRepository, CategoryClient categoryClient) {
        this.productRepository = productRepository;
        this.categoryClient = categoryClient;
    }

    public Optional<Mono<ProductDto>> findById(String productId) {
        return productRepository.findById(productId)
                .map(product ->
                        categoryClient.getById(product.getCategoryId())
                                .map(categoryDto -> ProductDto.from(product, categoryDto))
                );
    }

    public Mono<List<ProductDto>> findAll() {
        return populateCategories(productRepository.findAll());
    }

    public Mono<List<ProductDto>> findAllByIds(List<String> productIds) {
        return populateCategories(productRepository.findAllById(productIds));
    }

    private Mono<List<ProductDto>> populateCategories(List<Product> products) {
        List<String> categoryIds = products.stream().map(Product::getCategoryId).distinct().toList();
        return Optional.of(categoryIds)
                .filter(Predicate.not(List::isEmpty))
                .map(ids -> categoryClient.getAllByIds(ids)
                        .map(categoryDtos -> products
                                .stream()
                                .map(product -> {
                                    var categoryDto = categoryDtos.stream()
                                            .filter(c -> c.getId().equals(product.getCategoryId()))
                                            .findFirst()
                                            .orElse(new CategoryDto(product.getCategoryId(), ""));
                                    return ProductDto.from(product, categoryDto);
                                })
                                .toList())
                )
                .orElse(Mono.just(Collections.emptyList()));

    }
}


