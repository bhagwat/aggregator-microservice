package com.ms.product;

import com.ms.product.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class ProductApplication {

    public static void main(String[] args) throws Exception {
        var ctx = SpringApplication.run(ProductApplication.class, args);
        ProductService productService = ctx.getBean(ProductService.class);
        productService.bootstrap();
    }
}
