package com.cagatay.curuk.orderservice.service;

import com.cagatay.curuk.orderservice.dto.OrderResponseDto;
import com.cagatay.curuk.orderservice.mapper.OrderMapper;
import com.cagatay.curuk.orderservice.model.Order;
import com.cagatay.curuk.orderservice.repository.OrderRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


@Slf4j
@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public OrderResponseDto getOrderById(UUID orderId) {
        Order order = orderRepository.findById(orderId).orElse(null);

        return orderMapper.toResponseDto(order);
    }

    @Transactional
    public Order createOrder(Order order) {
        try {
            Order savedOrder = orderRepository.save(order);
            log.info("Created order {}", savedOrder.getId());
            return savedOrder;
        } catch (Exception e) {
            log.error("Order could not be created. Error: {}", e.getMessage());
            return null;
        }
    }

    @Transactional
    public void deleteOrder(UUID orderId) {
        try {
            orderRepository.deleteById(orderId);
            log.info("Deleted order {}", orderId);
        } catch (Exception e) {
            log.error("Order could not be deleted. Error: {}", e.getMessage());
        }
    }
}