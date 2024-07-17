package com.microservice.Order.Domain.Infrastructure;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tbl_order_products")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderProducts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "int_id")
    private Integer id;

    @ManyToOne
    private OrdenEntity order;

    @ManyToOne
    private ProductEntity product;

    private int quantity;
}
