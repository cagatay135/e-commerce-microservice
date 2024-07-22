package com.cagatay.curuk.orderservice.repository;

import com.cagatay.curuk.orderservice.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrderRepository extends JpaRepository<Order, UUID> {
}