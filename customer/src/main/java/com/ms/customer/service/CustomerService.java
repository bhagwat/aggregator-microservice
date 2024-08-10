package com.ms.customer.service;

import com.ms.customer.entity.Address;
import com.ms.customer.entity.Customer;
import com.ms.customer.repository.CustomerRepository;
import com.opencsv.CSVReader;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.Reader;
import java.nio.file.Files;
import java.util.List;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    @Value("classpath:data/customers.csv")
    Resource customerCsv;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Transactional
    public void bootstrap() throws Exception {
        this.boostrapCustomers();
        customerRepository.findAll().forEach(System.out::println);
    }

    private void boostrapCustomers() throws Exception {
        readAllLines(customerCsv)
                .stream()
                .skip(1)
                .forEach(row -> {
                    Customer customer = new Customer();
                    customer.setId(Long.parseLong(row[0]));
                    customer.setName(row[1]);
                    Address address = new Address();
                    address.setCity(row[2]);
                    address.setZipCode(row[3]);
                    customer.setAddress(address);

                    customerRepository.save(customer);
                });
    }

    private List<String[]> readAllLines(Resource resource) throws Exception {
        try (Reader reader = Files.newBufferedReader(resource.getFile().toPath())) {
            try (CSVReader csvReader = new CSVReader(reader)) {
                return csvReader.readAll();
            }
        }
    }
}
