/*
 * Copyright (c) 2024.
 *
 * @author Bhagwat Kumar
 */

package com.ms.product.service;

import com.ms.product.entity.Product;
import com.ms.product.repository.ProductRepository;
import com.opencsv.CSVReader;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.Reader;
import java.nio.file.Files;
import java.util.List;

@Slf4j
@Service
public class BootstrapService {
    private final ProductRepository productRepository;

    @Value("classpath:data/products.csv")
    Resource productsCsv;

    public BootstrapService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Transactional
    public void bootstrap() throws Exception {
        this.boostrapCustomers();
        this.logAllProducts();
    }

    private void logAllProducts() {
        productRepository.findAll().forEach(product -> {
                    log.info("Product ID: {} Name: {} , Desc {}, Category {}",
                            product.getId(),
                            product.getName(),
                            product.getDescription(),
                            product.getCategoryId()
                    );
                }
        );
    }

    private void boostrapCustomers() throws Exception {
        readAllLines(productsCsv)
                .stream()
                .skip(1)
                .forEach(row -> {
                    Product product = new Product(row[0], row[1], row[2], row[3]);
                    productRepository.save(product);
                });
    }

    private List<String[]> readAllLines(Resource resource) throws Exception {
        try (Reader reader = Files.newBufferedReader(resource.getFile().toPath())) {
            try (CSVReader csvReader = new CSVReader(reader)) {
                return csvReader.readAll();
            }
        }
    }
}


