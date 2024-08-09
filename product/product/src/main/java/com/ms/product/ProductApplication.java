package com.ms.product;

import com.ms.product.repository.ProductRepository;
import com.ms.product.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class ProductApplication {

    public static void main(String[] args) {
        var ctx = SpringApplication.run(ProductApplication.class, args);
        ProductService productService = ctx.getBean(ProductService.class);
        productService.importFromCsv();

        ProductRepository productRepository = ctx.getBean(ProductRepository.class);
        productRepository.findAll().forEach(product -> {
                    log.info(String.format("Product ID: %s Name: %s , Desc %s", product.getId(), product.getName(), product.getDescription()));
                }
        );
    }
}
