package com.ms.order.controller;

import com.ms.order.entity.Order;
import com.ms.order.repository.OrderRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {
    private final OrderRepository orderRepository;

    public OrderController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @GetMapping("/{id}")
    Order findById(@PathVariable Long id) {
        System.out.println("findById:: " + id);
        return orderRepository.findById(id)
                .orElse(new Order());
    }

    @GetMapping
    List<Order> findAll() {
        return orderRepository.findAll();
    }

}
