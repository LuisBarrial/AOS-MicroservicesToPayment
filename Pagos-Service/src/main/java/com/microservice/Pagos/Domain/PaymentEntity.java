package com.microservice.Pagos.Domain;

import com.microservice.Pagos.Core.BaseAudit;
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
public class PaymentEntity extends BaseAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private int orderId;

    private String city;

    private String departament;

    private String dni;

    private String addres;

    private String name;

    private String lastName;

    private String phone;

    private double price;

    private String stripeId;




}
