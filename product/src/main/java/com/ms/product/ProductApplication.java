/*
 * Copyright (c) 2024.
 *
 * @author Bhagwat Kumar
 */

package com.ms.product;

import com.ms.product.service.BootstrapService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class ProductApplication {

    public static void main(String[] args) throws Exception {
        var ctx = SpringApplication.run(ProductApplication.class, args);
        BootstrapService bootstrapService = ctx.getBean(BootstrapService.class);
        bootstrapService.bootstrap();
    }
}
