
/*
 * Copyright (c) 2024.
 *
 * @author Bhagwat Kumar
 */

package com.ms.category.util;

import com.ms.category.entity.Category;
import io.micronaut.serde.annotation.Serdeable;

/**
 * Light version of Category to be used in API response
 *
 * @param id   Category ID
 * @param name Category Name
 */
@Serdeable
public record CategoryDetail(String id, String name) {

    /**
     * Create CategoryDetail instance from Category entity
     *
     * @param category Category entity
     * @return CategoryDetail
     */
    public static CategoryDetail fromCategory(Category category) {
        return new CategoryDetail(category.getId(), category.getName());
    }
}
