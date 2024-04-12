package com.example.orderservice.web;

import com.example.orderservice.clients.CustomerRestClient;
import com.example.orderservice.clients.InventoryRestClient;
import com.example.orderservice.entities.Order;
import com.example.orderservice.repositories.OrderRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class OrderRestController {
    private OrderRepository orderRepository;
    private InventoryRestClient inventoryRestClient;
private CustomerRestClient customerRestClient;
    public OrderRestController(OrderRepository orderRepository, InventoryRestClient inventoryRestClient,CustomerRestClient customerRestClient) {
        this.orderRepository = orderRepository;
        this.inventoryRestClient = inventoryRestClient;
        this.customerRestClient=customerRestClient;
    }

    @GetMapping("/orders")
    public List<Order> findAllOrders(){
        List<Order> allOrders = orderRepository.findAll();
        allOrders.forEach(o->{
            o.setCustomer(customerRestClient.findCustomerById(o.getCustomerId()));
            o.getProductItems().forEach(pi->{
                pi.setProduct(inventoryRestClient.findProductById(pi.getProductId()));
            });
        });
        return allOrders;
    }
    @GetMapping("/orders/{id}")
    public Order findOrderById(@PathVariable String id){
        Order order = orderRepository.findById(id).get();
        order.getProductItems().forEach(pi->{
            pi.setProduct(inventoryRestClient.findProductById(pi.getProductId()));
        });
        return order;
    }
}
