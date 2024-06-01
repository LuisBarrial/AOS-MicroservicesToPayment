package com.microservice.Pagos.Domain.Infrastructure.DAO;

import com.microservice.Pagos.Domain.PaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PaymentDAO extends JpaRepository<PaymentEntity, UUID> {
}
