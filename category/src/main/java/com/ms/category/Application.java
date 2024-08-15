
/*
 * Copyright (c) 2024.
 *
 * @author Bhagwat Kumar
 */

package com.ms.category;

import com.ms.category.repository.CategoryRepository;
import com.ms.category.service.BootstrapService;
import com.ms.category.service.CategoryService;
import io.micronaut.runtime.Micronaut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Application {
    private static final Logger log = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        var ctx = Micronaut.run(Application.class, args);

        // Conditional bootstrap or use flyway for the data setup
        var bootstrapService = ctx.getBean(BootstrapService.class);
        bootstrapService.bootstrap();

        // log all the root categories
        var categoryService = ctx.getBean(CategoryService.class);
        categoryService.getRootCategories()
                .forEach(c -> log.info(c.toString()));

        // log all the bootstrapped categories
        var categoryRepository = ctx.getBean(CategoryRepository.class);
        categoryRepository.findAll()
                .forEach(c -> log.info(c.toString()));
    }
}