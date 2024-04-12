package com.example.orderservice.clients;

import com.example.orderservice.model.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "INV-SERVICE")
public interface InventoryRestClient {
    @GetMapping("/api/products")
    List<Product> getAllProducts();
    @GetMapping("/api/products/{id}")
    Product findProductById(@PathVariable String id);
}