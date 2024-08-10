package com.ms.aggregator.controller;

import com.ms.aggregator.dto.OrderWithCustomerAndProductDetail;
import com.ms.aggregator.service.AggregatorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;

@Slf4j
@RestController
public class AggregatorController {
    private final AggregatorService aggregatorService;

    @Autowired
    public AggregatorController(AggregatorService aggregatorService) {
        this.aggregatorService = aggregatorService;
    }

    @GetMapping("/aggregate/{id}")
    public OrderWithCustomerAndProductDetail aggregate(@PathVariable Long id) throws ExecutionException, InterruptedException {
        return aggregatorService.getOrderDetails(id);
    }
}
