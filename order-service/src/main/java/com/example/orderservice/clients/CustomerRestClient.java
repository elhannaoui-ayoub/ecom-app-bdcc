package com.example.orderservice.clients;

import com.example.orderservice.model.Customer;
import com.example.orderservice.model.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;


@FeignClient(name = "CUSTOMER-SERVICE")
public interface CustomerRestClient {
    @GetMapping("/customers")
    List<Product> getAllCustomers();
    @GetMapping("/customers/{id}")
    Customer findCustomerById(@PathVariable String id);
}
