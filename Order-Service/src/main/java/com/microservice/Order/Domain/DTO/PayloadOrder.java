package com.microservice.Order.Domain.DTO;

public record PayloadOrder(int ProductId, int Quantity, int UserId, Integer OrderId) {
}
