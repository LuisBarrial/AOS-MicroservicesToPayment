package com.microservice.Order.Domain.Infrastructure.DAO;

import com.microservice.Order.Domain.Infrastructure.OrderProducts;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IOrderProducts extends JpaRepository<OrderProducts, Integer> {
    List<OrderProducts> findByOrderIdAndProductId(Integer orderId, Integer productId);

}
