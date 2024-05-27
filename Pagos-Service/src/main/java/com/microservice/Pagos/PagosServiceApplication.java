package com.microservice.Pagos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class PagosServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PagosServiceApplication.class, args);
	}

}
