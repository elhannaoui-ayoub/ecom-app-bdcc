package com.example.customerservice.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class Customer {
    @Id
    private String id;
    private String firstName;
    private String lastName;

    private String email;

}