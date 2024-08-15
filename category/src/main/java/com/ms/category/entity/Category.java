
/*
 * Copyright (c) 2024.
 *
 * @author Bhagwat Kumar
 */

package com.ms.category.entity;

import io.micronaut.core.annotation.NonNull;
import jakarta.persistence.*;

import java.util.List;

/**
 * Category Entity for storing hierarchical Category tree
 */
@Entity
@Table(name = "categories")
public class Category {
    /**
     * Assigned ID
     */
    @Id
    private String id;

    /**
     * Category Name
     */
    @NonNull
    private String name;

    /**
     * Parent category
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_category_id")
    private Category parentCategory;

    /**
     * List of sub-categories
     */
    @OneToMany(mappedBy = "parentCategory", cascade = CascadeType.ALL)
    private List<Category> subCategories;

    public Category() {
    }

    public Category(String name) {
        this.name = name;
        this.parentCategory = null;
    }

    public Category(String name, Category parentCategory) {
        this.name = name;
        this.parentCategory = parentCategory;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getParentCategory() {
        return parentCategory;
    }

    public void setParentCategory(Category parentCategory) {
        this.parentCategory = parentCategory;
    }

    public List<Category> getSubCategories() {
        return subCategories;
    }

    public void setSubCategories(List<Category> subCategories) {
        this.subCategories = subCategories;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", parentCategory=" + parentCategory +
                '}';
    }
}