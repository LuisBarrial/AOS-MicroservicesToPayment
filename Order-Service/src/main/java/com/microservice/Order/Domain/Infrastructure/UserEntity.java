package com.microservice.Order.Domain.Infrastructure;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "tbl_user")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "int_id")
    private Integer id;

    @Column(name = "vch_email", nullable = false)
    private String email;

    @Column(name = "vch_password", nullable = false)
    private String password;

    @Column(name = "vch_name", nullable = false)
    private String name;

    @Column(name = "vch_lastname", nullable = false)
    private String lastName;

    @Column(name = "dat_deleted_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date deletedAt;

    @Column(name = "int_created_by", nullable = true)
    private Integer createdBy;

    @Column(name = "int_updated_by", nullable = true)
    private Integer updatedBy;
}
