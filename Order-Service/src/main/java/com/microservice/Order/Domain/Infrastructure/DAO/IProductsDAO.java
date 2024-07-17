package com.microservice.Order.Domain.Infrastructure.DAO;

import com.microservice.Order.Domain.Infrastructure.OrderProducts;
import com.microservice.Order.Domain.Infrastructure.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProductsDAO extends JpaRepository<ProductEntity, Integer> {
}
