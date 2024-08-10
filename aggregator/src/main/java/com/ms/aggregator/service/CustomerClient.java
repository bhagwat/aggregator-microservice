package com.ms.aggregator.service;

import com.ms.aggregator.dto.Customer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Primary;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Primary
@FeignClient(name = "customer-service", url = "${customer.service.url}", fallback = CustomerClientFallback.class)
public interface CustomerClient {
    @GetMapping("/customer/{id}")
    Customer getById(@PathVariable("id") Long id);

    @GetMapping("/customer")
    List<Customer> getAll();
}
