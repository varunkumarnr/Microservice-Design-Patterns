package com.vk.Learning.service;


import com.vk.Learning.dao.CustomerDAO;
import com.vk.Learning.dto.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.List;

@Service
public class CustomerService {
    @Autowired
    private CustomerDAO customerDAO;

    public List<Customer> loadAllCustomers() {
        long start = System.currentTimeMillis();
        List<Customer> customers =  customerDAO.getCustomers();
        long end = System.currentTimeMillis();
        System.out.println("Difference" + (end - start));
        return customers;
    }

    public Flux<Customer> loadAllCustomersFlux() {
        long start = System.currentTimeMillis();
        Flux<Customer> customers =  customerDAO.getCustomerFluxStream();
        long end = System.currentTimeMillis();
        System.out.println("Difference " + (end - start));
        return customers;
    }
}
