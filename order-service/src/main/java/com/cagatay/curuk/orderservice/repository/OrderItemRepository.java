package com.cagatay.curuk.orderservice.repository;

import com.cagatay.curuk.orderservice.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrderItemRepository extends JpaRepository<OrderItem, UUID> {
}