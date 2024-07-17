package com.microservice.Order.Domain.Infrastructure;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "pago")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class PaymentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private int orderId;

    private String city;

    private String departament;

    private String dni;

    private String addres;

    private String email;

    private String name;

    private String lastName;

    private String phone;

    private double price;

    private String stripeId;




}
