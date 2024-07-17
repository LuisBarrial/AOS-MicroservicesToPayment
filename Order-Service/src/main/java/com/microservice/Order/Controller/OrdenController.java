package com.microservice.Order.Controller;

import com.microservice.Order.Domain.Infrastructure.DAO.IOrdenDAO;
import com.microservice.Order.Domain.Infrastructure.DAO.IOrderProducts;
import com.microservice.Order.Domain.Infrastructure.DAO.IProductsDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("order")
public class OrdenController {
    @Autowired
    private IOrdenDAO iOrdenDAO;

    @Autowired
    private IOrderProducts iOrderProducts;

    @Autowired
    private IProductsDAO iProductsDAO;


}
