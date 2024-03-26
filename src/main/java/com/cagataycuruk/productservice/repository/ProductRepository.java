package com.cagataycuruk.productservice.repository;

import com.cagataycuruk.productservice.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {
}