package com.microservice.Order.Domain.Infrastructure;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "tbl_order")
public class OrdenEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "int_id")
    private Integer id;

    @ManyToOne
    private UserEntity user;

    @Column(name = "amount", nullable = false, precision = 10, scale = 2)
    private BigDecimal price;
}
