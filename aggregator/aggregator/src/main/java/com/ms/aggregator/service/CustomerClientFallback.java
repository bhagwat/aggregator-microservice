package com.ms.aggregator.service;

import java.util.List;

import com.ms.aggregator.dto.Customer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class CustomerClientFallback implements CustomerClient {
    @Override
    public Customer getById(Long id) {
        log.error("Trigger CustomerClientFallback.getById");
        return new Customer(id, "", null);
    }

    @Override
    public List<Customer> getAll() {
        log.error("Trigger CustomerClientFallback.getAll");
        return List.of();
    }
}
