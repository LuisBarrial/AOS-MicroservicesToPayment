package com.microservice.Pagos.Controller;

import com.microservice.Pagos.Domain.PaymentEntity;
import com.microservice.Pagos.Domain.Infrastructure.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping
    public ResponseEntity<PaymentEntity> savePayment(@RequestBody PaymentEntity paymentEntity){
        return ResponseEntity.ok(paymentService.createPayment(paymentEntity));
    }

    @GetMapping("getall")
    public ResponseEntity<?> getAllPayment(){
        return ResponseEntity.ok(paymentService.getAllPayments());
    }
}
