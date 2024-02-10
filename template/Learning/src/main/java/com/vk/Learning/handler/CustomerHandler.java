package com.vk.Learning.handler;

import com.vk.Learning.dao.CustomerDAO;
import com.vk.Learning.dto.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
@Component
public class CustomerHandler {
    @Autowired
    private CustomerDAO customerDAO;

    public Mono<ServerResponse> loadCustomers(ServerRequest request) {
        Flux<Customer> customerFlux = customerDAO.getCustomerList();
        return ServerResponse.ok().body(customerFlux, Customer.class);
    }
    public Mono<ServerResponse> loadCustomer(ServerRequest request) {
        int cId = Integer.parseInt(request.pathVariable("input"));
        Mono<Customer> customerMono= customerDAO.getCustomerList()
                                        .filter(i -> i.getId() == cId).next();
        return ServerResponse.ok().body(customerMono, Customer.class);
    }
    public Mono<ServerResponse> addCustomer(ServerRequest request) {
        Mono<Customer> customerMono = request.bodyToMono(Customer.class);
        Mono<String> saveCustomer = customerMono.map(e -> e.getId()  + " : " + e.getName());
        return  ServerResponse.ok().body(saveCustomer, String.class);
    }
}
