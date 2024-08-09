package com.ms.product.controller;

import java.util.List;

import com.ms.product.entity.Product;
import com.ms.product.errorHanlder.ProductNotFoundException;
import com.ms.product.repository.ProductRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
public class ProductController {
    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping("/{id}")
    Product findById(@PathVariable String id) {
        System.out.println("findById:: " + id);
        return productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
    }

    @GetMapping
    List<Product> findAll() {
        return productRepository.findAll();
    }
}
