/*
 * Copyright (c) 2024.
 *
 * @author Bhagwat Kumar
 */

package com.ms.product.service;

import com.ms.product.dto.CategoryDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.List;

@Service
public class CategoryClient {
    private static final Logger log = LoggerFactory.getLogger(CategoryClient.class);

    private final WebClient webClient;

    @Autowired
    public CategoryClient(@Value("${category.service.url}") String categoryServiceUrl) {
        log.info("categoryServiceUrl: {}", categoryServiceUrl);
        this.webClient = WebClient.builder()
                .baseUrl(categoryServiceUrl)
                .build();
    }

    /**
     * Get category by ID
     *
     * @param id category id
     * @return Mono Category
     */
    public Mono<CategoryDto> getById(String id) {
        log.info("CategoryClient.getById called with id: {}", id);
        return webClient.get()
                .uri(uriBuilder -> uriBuilder.path("/{id}").build(id))
                .retrieve()
                .bodyToMono(CategoryDto.class)
                .onErrorResume(e -> Mono.just(new CategoryDto(id, "")));
    }

    /**
     * Get the categories for given IDs
     *
     * @param ids List of Category ID
     * @return Mono of List of CategoryDto
     */
    public Mono<List<CategoryDto>> getAllByIds(List<String> ids) {
        log.info("CategoryClient.getAllByIds called with ids: {}", ids);
        return webClient.get()
                .uri(uriBuilder -> uriBuilder.path("/").queryParam("ids", ids).build())
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<CategoryDto>>() {
                })
                .onErrorResume(_ -> Mono.just(Collections.emptyList()));
    }

    /**
     * Get all the categories
     *
     * @return Mono of List of CategoryDTO
     */
    public Mono<List<CategoryDto>> getAll() {
        log.info("CategoryClient.getAll called");
        return webClient.get()
                .uri("/all")
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<CategoryDto>>() {
                })
                .onErrorResume(_ -> Mono.just(Collections.emptyList()));
    }
}
