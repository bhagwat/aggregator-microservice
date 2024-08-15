
/*
 * Copyright (c) 2024.
 *
 * @author Bhagwat Kumar
 */

package com.ms.category.util;

import com.ms.category.entity.Category;
import io.micronaut.serde.annotation.Serdeable;

import java.util.List;
import java.util.Optional;

/**
 * Details version of Category with details of sub-categories and parent category to be used in API endpoints
 *
 * @param id             Category ID
 * @param name           Category Name
 * @param subCategories  List of associated subcategories
 * @param parentCategory Parent Category which is null for the root categories
 */
@Serdeable
public record CategoryDTO(String id, String name, List<CategoryDetail> subCategories, CategoryDetail parentCategory) {

    /**
     * Utility to create CategoryDTO instance from a given Category entity
     *
     * @param category category entity
     * @return CategoryDTO instance
     */
    public static CategoryDTO fromCategory(Category category) {
        return new CategoryDTO(
                category.getId(),
                category.getName(),

                category.getSubCategories()
                        .stream()
                        .map(subCategory -> new CategoryDetail(subCategory.getId(), subCategory.getName()))
                        .toList(),

                Optional.ofNullable(category.getParentCategory())
                        .map(CategoryDetail::fromCategory)
                        .orElse(null));
    }
}
