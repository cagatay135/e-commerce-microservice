package com.cagataycuruk.productservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SpringBootApplication
public class ProductServiceApplication {

	private static final Logger logger = LoggerFactory.getLogger(ProductServiceApplication.class);

	public static void main(String[] args) {
		logger.debug("Product Service Application is starting...");
		SpringApplication.run(ProductServiceApplication.class, args);
		logger.debug("Product Service Application started successfully!");
	}

}
