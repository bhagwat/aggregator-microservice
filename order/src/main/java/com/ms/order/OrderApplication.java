package com.ms.order;

import com.ms.order.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class OrderApplication {

    public static void main(String[] args) throws Exception {
        var ctx = SpringApplication.run(OrderApplication.class, args);
        ctx.getBean(OrderService.class).bootstrap();
    }
}
