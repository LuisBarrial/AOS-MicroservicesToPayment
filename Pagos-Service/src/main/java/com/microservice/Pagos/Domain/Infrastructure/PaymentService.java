package com.microservice.Pagos.Domain.Infrastructure;

import com.microservice.Pagos.Application.Repository.PaymentRepository;
import com.microservice.Pagos.Domain.PaymentEntity;
import com.microservice.Pagos.Domain.Infrastructure.DAO.PaymentDAO;
import com.stripe.Stripe;
import com.stripe.model.PaymentIntent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class PaymentService implements PaymentRepository {

    private PaymentDAO paymentDAO;



    @Autowired
    public PaymentService(PaymentDAO paymentDAO, @Value("${stripe.apikey}") String secretKey) {
        this.paymentDAO = paymentDAO;
        Stripe.apiKey = secretKey;
    }


    @Override
    public PaymentEntity createPayment(PaymentEntity entity) {
        List<String> paymentMethodTypes = List.of("card");
        int paymentPrice =(int)(Double.parseDouble(String.valueOf(entity.getPrice()))*100);
        System.out.println(paymentPrice);
        Map<String, Object> params = new HashMap<>();
        params.put("amount", paymentPrice);
        params.put("currency", "pen");
        params.put("customer", "cus_QBTxQy2Lgt6xAs"); // Asociar el PaymentIntent con el Customer existente
        params.put("payment_method_types", paymentMethodTypes);
        params.put("payment_method","pm_card_visa");

        try{
           var element = PaymentIntent.create(params).confirm();
           entity.setStripeId(element.getId());

        }
        catch (Exception e){
            throw new RuntimeException("Error al pagar en Stripe");
        }
        return paymentDAO.save(entity);
    }

    @Override
    public PaymentEntity getPayment(UUID id) {
        return paymentDAO.findById(id).orElse(null);
    }
}
