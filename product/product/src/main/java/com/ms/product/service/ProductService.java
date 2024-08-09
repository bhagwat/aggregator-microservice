package com.ms.product.service;

import com.ms.product.entity.Product;
import com.ms.product.repository.ProductRepository;
import com.ms.product.util.CsvDataLoader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    @Value("classpath:data/products.csv")
    Resource resource;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void importFromCsv() {
        productRepository.saveAll(CsvDataLoader.loadObjectList(Product.class, resource));
    }
}


