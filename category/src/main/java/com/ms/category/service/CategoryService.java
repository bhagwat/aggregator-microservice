
/*
 * Copyright (c) 2024.
 *
 * @author Bhagwat Kumar
 */

package com.ms.category.service;

import com.ms.category.entity.Category;
import com.ms.category.repository.CategoryRepository;
import com.ms.category.util.CategoryDTO;
import com.ms.category.util.CategoryDetail;
import jakarta.inject.Singleton;
import jakarta.transaction.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * Category Service with methods to work with Category JPA entity
 */
@Singleton
public class CategoryService {

    final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    /**
     * Get Root categories
     *
     * @return List of CategoryDTO
     */
    @Transactional
    public List<CategoryDTO> getRootCategories() {
        return categoryRepository.findRootCategories()
                .stream()
                .map(CategoryDTO::fromCategory)
                .toList();
    }

    /**
     * Get All categories
     *
     * @return List of CategoryDTO
     */
    @Transactional
    public List<CategoryDTO> getAllCategories() {
        return categoryRepository.findAll()
                .stream()
                .map(CategoryDTO::fromCategory)
                .toList();
    }

    /**
     * Get Category by ID
     *
     * @return CategoryDTO instance
     */
    @Transactional
    public Optional<CategoryDTO> findById(String id) {
        return categoryRepository.findById(id).map(CategoryDTO::fromCategory);
    }

    /**
     * Get Categories by ID in given list
     *
     * @return CategoryDTO list
     */
    @Transactional
    public List<CategoryDTO> findAllById(List<String> ids) {
        return categoryRepository.findAllByIdInList(ids)
                .stream()
                .map(CategoryDTO::fromCategory)
                .toList();
    }

    /**
     * Get ancestors of a Category
     *
     * @param id Category Id
     * @return List of CategoryDetail as ancestors (immediate parent as first element)
     */
    @Transactional
    public List<CategoryDetail> getAncestors(String id) {
        return categoryRepository.findById(id)
                .map(this::getAncestors)
                .map(this::categoryToDetailDTO)
                .orElse(Collections.emptyList());
    }

    /**
     * Get Children of a Category
     *
     * @param id Category Id
     * @return List of CategoryDetail as children
     */
    @Transactional
    public List<CategoryDetail> getChildren(String id) {
        return categoryRepository.findById(id)
                .map(Category::getSubCategories)
                .map(this::categoryToDetailDTO)
                .orElse(Collections.emptyList());
    }

    /**
     * List of ancestors of the given category
     *
     * @param category Category entity
     * @return List of Categories as ancestors (immediate parent as first element)
     */
    private List<Category> getAncestors(Category category) {
        List<Category> ancestors = new ArrayList<>();
        Category current = category;
        while (Optional.ofNullable(current.getParentCategory()).isPresent()) {
            ancestors.add(current.getParentCategory());
            current = current.getParentCategory();
        }
        return ancestors;
    }

    /**
     * Convert List of Category to List of CategoryDetail
     *
     * @param categories List of Categories
     * @return list of CategoryDetail
     */
    private List<CategoryDetail> categoryToDetailDTO(List<Category> categories) {
        return categories
                .stream()
                .map(c -> new CategoryDetail(c.getId(), c.getName()))
                .toList();
    }
}
