
/*
 * Copyright (c) 2024.
 *
 * @author Bhagwat Kumar
 */

package com.ms.category.repository;

import com.ms.category.entity.Category;
import io.micronaut.data.annotation.Query;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;

import java.util.List;

/**
 * Category repository
 */
@Repository
public interface CategoryRepository extends CrudRepository<Category, String> {
    @Query("select c from Category c where c.parentCategory is null ")
    List<Category> findRootCategories();

    List<Category> findAllByIdInList(List<String> ids);
}