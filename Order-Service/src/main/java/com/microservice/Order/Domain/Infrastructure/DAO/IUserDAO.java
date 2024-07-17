package com.microservice.Order.Domain.Infrastructure.DAO;
import com.microservice.Order.Domain.Infrastructure.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserDAO extends JpaRepository<UserEntity, Integer> {
}