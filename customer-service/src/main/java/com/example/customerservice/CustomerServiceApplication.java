package com.example.customerservice;

import com.example.customerservice.entities.Customer;
import com.example.customerservice.repositories.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.UUID;

@SpringBootApplication
public class CustomerServiceApplication {

	private final CustomerRepository customerRepository;

	public CustomerServiceApplication(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}

	public static void main(String[] args) {


		SpringApplication.run(CustomerServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(){

		return args -> {
			Customer customer1 = Customer.builder()
					.id("C01")
					.firstName("Hassan")
					.lastName("Elhoumi")
					.email("hassan@gmail.com")
					.build();
			customerRepository.save(customer1);
			Customer customer2 = Customer.builder()
					.id("C02")
					.firstName("Ayoub")
					.lastName("Elhannaoui")
					.email("ayoub@gmail.com")
					.build();
			customerRepository.save(customer2);
		};
	}
}
