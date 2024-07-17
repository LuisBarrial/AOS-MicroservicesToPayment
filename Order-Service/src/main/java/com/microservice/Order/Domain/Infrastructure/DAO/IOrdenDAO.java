package com.microservice.Order.Domain.Infrastructure.DAO;

import com.microservice.Order.Domain.Infrastructure.OrdenEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IOrdenDAO extends JpaRepository<OrdenEntity, Integer> {
}
