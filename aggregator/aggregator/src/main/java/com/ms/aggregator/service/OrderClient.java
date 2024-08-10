package com.ms.aggregator.service;

import java.util.List;

import com.ms.aggregator.dto.Order;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Primary;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Primary
@FeignClient(name = "order-service", url = "${order.service.url}")
public interface OrderClient {
    @GetMapping("/order/{id}")
    Order getById(@PathVariable("id") Long id);

    @GetMapping("/order")
    List<Order> getAll();
}
