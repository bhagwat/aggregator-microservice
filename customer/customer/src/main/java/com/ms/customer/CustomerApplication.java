package com.ms.customer;

import com.ms.customer.service.CustomerService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CustomerApplication {

    public static void main(String[] args) throws Exception {
        var ctx = SpringApplication.run(CustomerApplication.class, args);
        ctx.getBean(CustomerService.class).bootstrap();
    }
}
