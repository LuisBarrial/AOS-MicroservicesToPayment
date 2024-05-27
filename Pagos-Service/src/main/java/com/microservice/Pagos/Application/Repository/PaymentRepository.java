package com.microservice.Pagos.Application.Repository;


import com.microservice.Pagos.Domain.PaymentEntity;

import java.util.UUID;

public interface PaymentRepository{
    PaymentEntity createPayment(PaymentEntity entity);
    PaymentEntity getPayment(UUID id);

}
