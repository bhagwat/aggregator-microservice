
/*
 * Copyright (c) 2024.
 *
 * @author Bhagwat Kumar
 */

package com.ms.category.service;

import com.ms.category.entity.Category;
import com.ms.category.repository.CategoryRepository;
import io.micronaut.context.annotation.Context;
import io.micronaut.context.annotation.Value;
import jakarta.transaction.Transactional;
import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Context
public class BootstrapService {
    private static final Logger log = LoggerFactory.getLogger(BootstrapService.class);

    @Value("${flyway.datasources.default.enabled:true}")
    Boolean useFlyway;

    final CategoryRepository categoryRepository;

    public BootstrapService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    /**
     * Bootstrap categories
     * Not that: the bootstrap is disabled in favour of Flyway migration tool
     */
    public void bootstrap() {
        if (useFlyway) {
            log.info("Flyway migration is enabled. Skipping custom bootstrap...  ");
            return;
        }

        log.info("Flyway migration is disabled. Starting Custom bootstrap...  ");
        log.info("Make sure 'hibernate.hbm2ddl.auto' is set to a valid value e.g. 'create-drop' in application.yaml");

        // Root categories
        Category electronics = new Category("Electronics");
        electronics.setId("electronics");
        saveCategory(electronics);

        // Subcategories of Electronics
        Category computers = new Category("Computers", electronics);
        computers.setId("computers");
        saveCategory(computers);

        Category laptops = new Category("Laptops", computers);
        laptops.setId("laptops");
        saveCategory(laptops);

        Category desktops = new Category("Desktops", computers);
        desktops.setId("desktops");
        saveCategory(desktops);

        Category smartphones = new Category("Smartphones", electronics);
        smartphones.setId("smartphones");
        saveCategory(smartphones);

        // Subcategories of Electronics
        Category accessories = new Category("Accessories", electronics);
        accessories.setId("accessories");
        saveCategory(accessories);

        Category mouse = new Category("Mouse", accessories);
        mouse.setId("mouse");
        saveCategory(mouse);

        Category speaker = new Category("Speaker", accessories);
        speaker.setId("speaker");
        saveCategory(speaker);

        Category joystick = new Category("Joystick", accessories);
        joystick.setId("joystick");
        saveCategory(joystick);

        Category wiredMouse = new Category("Wired Mouse", mouse);
        wiredMouse.setId("wiredMouse");
        saveCategory(wiredMouse);

        Category bluetoothMouse = new Category("Bluetooth Mouse", mouse);
        bluetoothMouse.setId("bluetoothMouse");
        saveCategory(bluetoothMouse);

        Category headset = new Category("Headset", speaker);
        headset.setId("headset");
        saveCategory(headset);

        Category headphone = new Category("Headphone", speaker);
        headphone.setId("headphone");
        saveCategory(headphone);
    }

    /**
     * Save/Update category and logs validation errors if any
     *
     * @param category Category entity to save/update
     */
    @Transactional
    public void saveCategory(Category category) {
        try {
            categoryRepository.save(category);
        } catch (ConstraintViolationException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }
}
