/*
 * Copyright (c) 2024.
 *
 * @author Bhagwat Kumar
 */

package com.ms.order.controller;

import com.ms.order.entity.Order;
import com.ms.order.errorHandler.OrderNotFoundException;
import com.ms.order.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/order")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    /**
     * Get order by ID
     *
     * @param id Order ID
     * @return Order instance or Order not found response
     */
    @GetMapping("/{id}")
    Order findOrderById(@PathVariable Long id) {
        log.info("OrderController.findById:: {}", id);
        return orderService.findById(id).orElseThrow(() -> new OrderNotFoundException(id));
    }

    /**
     * Get orders by given IDs
     *
     * @param ids List of order IDs
     * @return List of Order
     */
    @GetMapping
    List<Order> findOrdersByIds(@RequestParam List<Long> ids) {
        log.info("OrderController.findOrdersByIds:: {}", ids);
        return orderService.findAllByIds(ids);
    }

    /**
     * Get orders with pagination support
     *
     * @return List of Orders matching the pagination params
     */
    @GetMapping("/all")
    Page<Order> findAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(defaultValue = "lastUpdated,desc") String[] sort) {
        String sortField = sort[0];
        Sort.Direction direction = sort[1].equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
        Pageable pageable = PageRequest.of(page, pageSize, Sort.by(direction, sortField));
        return orderService.findAll(pageable);
    }
}
