package com.microservice.Order.Domain.Infrastructure;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "tbl_product")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "int_id")
    private Integer id;

    @Column(name = "vch_name", nullable = false, length = 100)
    private String name;

    @Column(name = "vch_description", nullable = false, length = 3000)
    private String description;

    @Column(name = "int_stock", nullable = false)
    private Integer stock;

    @Column(name = "dec_price", nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    @Column(name = "dat_deleted_at")
    private LocalDateTime deletedAt;

    @ManyToOne
    @JoinColumn(name = "int_created_by", nullable = false)
    private UserEntity createdBy;

    @ManyToOne
    @JoinColumn(name = "int_updated_by")
    private UserEntity updatedBy;
}
