
/*
 * Copyright (c) 2024.
 *
 * @author Bhagwat Kumar
 */

package com.ms.category.controller;

import com.ms.category.service.CategoryService;
import com.ms.category.util.CategoryDTO;
import com.ms.category.util.CategoryDetail;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.annotation.QueryValue;

import java.util.List;

/**
 * REST endpoints for product category resource
 */
@Controller("/category")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    /**
     * Get Root Categories list of CategoryDTO
     *
     * @return List of CategoryDTO
     */
    @Get("/all")
    List<CategoryDTO> getAllCategories() {
        return categoryService.getAllCategories();
    }

    /**
     * Get Root Categories list of CategoryDTO
     *
     * @return List of CategoryDTO
     */
    @Get("/root")
    List<CategoryDTO> getRootCategories() {
        return categoryService.getRootCategories();
    }

    /**
     * Get Category detail by ID
     *
     * @param id Category ID
     * @return CategoryDTO
     */
    @Get("/{id}")
    HttpResponse<CategoryDTO> getCategoryById(@PathVariable String id) {
        return categoryService.findById(id)
                .map(HttpResponse::ok)
                .orElse(HttpResponse.notFound());
    }

    /**
     * Get Category detail by ID
     *
     * @param ids Category IDs
     * @return List of CategoryDTO
     */
    @Get
    List<CategoryDTO> getCategoriesByIds(@QueryValue List<String> ids) {
        return categoryService.findAllById(ids);
    }

    /**
     * Get Ancestors of a category
     *
     * @return List of CategoryDetail
     */
    @Get("{id}/ancestors")
    List<CategoryDetail> getAncestors(@PathVariable String id) {
        return categoryService.getAncestors(id);
    }

    /**
     * Get Children of a category
     *
     * @return List of CategoryDetail
     */
    @Get("{id}/children")
    List<CategoryDetail> getChildren(@PathVariable String id) {
        return categoryService.getChildren(id);
    }
}

