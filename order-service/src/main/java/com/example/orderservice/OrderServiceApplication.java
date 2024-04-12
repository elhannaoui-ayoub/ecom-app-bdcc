package com.example.orderservice;
import java.util.Random;


import com.example.orderservice.clients.InventoryRestClient;
import com.example.orderservice.entities.Order;
import com.example.orderservice.entities.OrderState;
import com.example.orderservice.entities.ProductItem;
import com.example.orderservice.model.Product;
import com.example.orderservice.repositories.OrderRepository;
import com.example.orderservice.repositories.ProductItemRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;
import java.util.UUID;


@SpringBootApplication
@EnableFeignClients
public class OrderServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderServiceApplication.class, args);
	}
	@Bean
	CommandLineRunner commandLineRunner(
			OrderRepository orderRepository,
			ProductItemRepository productItemRepository,
			InventoryRestClient inventoryRestClient
	){
		return args -> {
			//List<Product> allProducts = inventoryRestClient.getAllProducts();
			List<String> productsIds = List.of("P01","P02","P03");
			List<String> customersIds = List.of("C01","C02");
			for (int i = 0; i < 5; i++) {
				Random random = new Random();
				Order order = Order.builder()
						.id(UUID.randomUUID().toString())
						.date(LocalDate.now())
						.customerId(customersIds.get(random.nextInt(2)))
						.state(OrderState.PENDING)
						.build();
				Order savedOrder = orderRepository.save(order);
				productsIds.forEach(pId->{
					ProductItem productItem = ProductItem.builder()
							.productId(pId)
							.quantity(new Random().nextInt(20))
							.price(Math.random()*6000+100)
							.order(savedOrder)
							.build();
					productItemRepository.save(productItem);
				});

			}
		};
	}

}
