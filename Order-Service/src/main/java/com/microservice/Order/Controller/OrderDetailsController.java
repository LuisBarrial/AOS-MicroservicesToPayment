package com.microservice.Order.Controller;

import com.microservice.Order.Domain.DTO.PayloadOrder;
import com.microservice.Order.Domain.Infrastructure.DAO.IOrdenDAO;
import com.microservice.Order.Domain.Infrastructure.DAO.IOrderProducts;
import com.microservice.Order.Domain.Infrastructure.DAO.IProductsDAO;
import com.microservice.Order.Domain.Infrastructure.DAO.IUserDAO;
import com.microservice.Order.Domain.Infrastructure.OrdenEntity;
import com.microservice.Order.Domain.Infrastructure.OrderProducts;
import com.microservice.Order.Domain.Infrastructure.PaymentEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

@RestController
@RequestMapping("orderdetails")
public class OrderDetailsController {
    @Autowired
    private IOrdenDAO iOrdenDAO;

    @Autowired
    private IOrderProducts iOrderProducts;

    @Autowired
    private IProductsDAO iProductsDAO;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private IUserDAO iUserDAO;

    @PostMapping
    public ResponseEntity<?> createOrderDetails(@RequestBody PayloadOrder payloadOrder){
        var products = this.iProductsDAO.findById(payloadOrder.ProductId()).orElse(null);
        Integer orderId = null;
        if(payloadOrder.OrderId() == null ){
            var user = this.iUserDAO.findById(payloadOrder.UserId()).orElse(null);
            OrdenEntity o = new OrdenEntity();
            o.setUser(user);
            o.setPrice(products.getPrice());
            var order = this.iOrdenDAO.save(o);
            orderId=order.getId();
        }
        if(orderId == null){
            orderId = payloadOrder.OrderId();
        }
        var productDetails = new OrderProducts();
        var order = iOrdenDAO.findById(orderId).orElse(null);
        order.setPrice(order.getPrice().add(products.getPrice().multiply(BigDecimal.valueOf(payloadOrder.Quantity()))));
        var exist = this.iOrderProducts.findByOrderIdAndProductId(payloadOrder.OrderId(), payloadOrder.ProductId());
        if(!exist.isEmpty()){
            throw new RuntimeException("Error el detalle ya existe");
        }
        this.iOrdenDAO.save(order);
        productDetails.setOrder(order);
        productDetails.setProduct(products);
        productDetails.setQuantity(payloadOrder.Quantity());
        this.iOrderProducts.save(productDetails);
        return new ResponseEntity<>(order.getId(),HttpStatus.CREATED);
    }
    @PostMapping("checkout")
    public ResponseEntity<?> checkout(){
        String url = "http://localhost:8090/payment";

        // Crear el objeto que deseas enviar en el cuerpo de la solicitud
        PaymentEntity request = new PaymentEntity();
        request.setOrderId(124);
        request.setCity("Pereira");
        request.setDepartament("Risaralda");
        request.setDni("3344556677");
        request.setAddres("Carrera 8 #15-30");
        request.setName("Sofía");
        request.setLastName("Vargas");
        request.setPhone("+57 317 444 5566");
        request.setPrice(185.00);
        request.setEmail("angeloxz.com@gmail.com");

        // Configurar headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Crear la entidad HTTP con el cuerpo JSON y headers
        HttpEntity entity = new HttpEntity(request, headers);

        // Enviar la solicitud POST
        String response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class).getBody();

        return ResponseEntity.ok().build();


    }
}
