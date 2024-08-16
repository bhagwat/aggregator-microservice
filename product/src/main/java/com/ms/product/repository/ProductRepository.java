/*
 * Copyright (c) 2024.
 *
 * @author Bhagwat Kumar
 */

package com.ms.product.repository;

import com.ms.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, String> {
}
