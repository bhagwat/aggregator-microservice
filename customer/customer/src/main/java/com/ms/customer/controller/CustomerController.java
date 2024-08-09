package com.ms.customer.controller;

import java.util.List;

import com.ms.customer.entity.Customer;
import com.ms.customer.repository.CustomerRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("customer")
public class CustomerController {
    private final CustomerRepository customerRepository;

    public CustomerController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @GetMapping("/{id}")
    Customer findById(@PathVariable Long id) {
        System.out.println("findById:: " + id);
        return customerRepository.findById(id)
                .orElse(new Customer());
    }

    @GetMapping
    List<Customer> findAll() {
        return customerRepository.findAll();
    }

}
