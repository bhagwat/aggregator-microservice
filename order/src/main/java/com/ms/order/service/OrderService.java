package com.ms.order.service;

import com.ms.order.entity.Order;
import com.ms.order.entity.OrderItem;
import com.ms.order.repository.OrderRepository;
import com.opencsv.CSVReader;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.Reader;
import java.nio.file.Files;
import java.util.List;

@Slf4j
@Service
public class OrderService {
    private final OrderRepository orderRepository;

    @Value("classpath:data/orders.csv")
    Resource ordersCsv;

    @Value("classpath:data/order-items.csv")
    Resource orderItemsCsv;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Transactional
    public void bootstrap() throws Exception {
        this.boostrapOrders();
        this.boostrapItems();
        orderRepository.findAll().forEach(System.out::println);
    }

    public void boostrapOrders() throws Exception {
        readAllLines(ordersCsv)
                .stream()
                .skip(1)
                .forEach(row -> {
                    Order order = new Order();
                    order.setId(Long.parseLong(row[0]));
                    order.setCouponCode(row[1]);
                    order.setCustomerId(row[2]);

                    orderRepository.save(order);
                });
    }

    public void boostrapItems() throws Exception {
        readAllLines(orderItemsCsv)
                .stream()
                .skip(1)
                .forEach(row -> {
                    orderRepository.findById(Long.parseLong(row[1]))
                            .ifPresent(order -> {
                                OrderItem orderItem = new OrderItem();
                                orderItem.setId(Long.parseLong(row[0]));
                                orderItem.setProductId(row[2]);
                                orderItem.setPrice(Double.parseDouble(row[4]));
                                order.getItems().add(orderItem);
                                orderRepository.save(order);
                            });
                });
    }

    public List<String[]> readAllLines(Resource resource) throws Exception {
        try (Reader reader = Files.newBufferedReader(resource.getFile().toPath())) {
            try (CSVReader csvReader = new CSVReader(reader)) {
                return csvReader.readAll();
            }
        }
    }
}
