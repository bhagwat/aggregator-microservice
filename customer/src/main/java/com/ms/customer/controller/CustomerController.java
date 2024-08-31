package com.ms.customer.controller;

import com.ms.customer.entity.Customer;
import com.ms.customer.repository.CustomerRepository;
import com.ms.customer.util.CustomerNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
                .orElseThrow(CustomerNotFoundException::new);
    }

    @GetMapping
    List<Customer> findAll() {
        return customerRepository.findAll();
    }

}
