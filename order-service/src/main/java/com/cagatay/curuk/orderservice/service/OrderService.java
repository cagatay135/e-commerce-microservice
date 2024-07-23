package com.cagatay.curuk.orderservice.service;

import com.cagatay.curuk.orderservice.dto.OrderItemRequestDto;
import com.cagatay.curuk.orderservice.dto.OrderItemResponseDto;
import com.cagatay.curuk.orderservice.dto.OrderItemUpdateDto;
import com.cagatay.curuk.orderservice.dto.OrderResponseDto;
import com.cagatay.curuk.orderservice.enums.OrderStatus;
import com.cagatay.curuk.orderservice.exception.InsufficientStockException;
import com.cagatay.curuk.orderservice.exception.OrderItemNotFoundException;
import com.cagatay.curuk.orderservice.exception.OrderNotFoundException;
import com.cagatay.curuk.orderservice.feign.InventoryClient;
import com.cagatay.curuk.orderservice.feign.ProductClient;
import com.cagatay.curuk.orderservice.mapper.OrderItemMapper;
import com.cagatay.curuk.orderservice.mapper.OrderMapper;
import com.cagatay.curuk.orderservice.model.Order;
import com.cagatay.curuk.orderservice.model.OrderItem;
import com.cagatay.curuk.orderservice.repository.OrderItemRepository;
import com.cagatay.curuk.orderservice.repository.OrderRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;


@Slf4j
@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final OrderItemRepository orderItemRepository;
    private final OrderItemMapper orderItemMapper;
    private final InventoryClient inventoryClient;

    private static final Logger logger = LoggerFactory.getLogger(OrderService.class);


    public OrderResponseDto getOrderById(UUID orderId) {
        return orderMapper.toResponseDto(findOrderById(orderId));
    }

    public Order findOrderById(UUID orderId) {
        return orderRepository.findById(orderId).orElseThrow(OrderNotFoundException::new);
    }

    public OrderResponseDto createOrder() {
        try {
            Order order = Order.builder()
                    .status(OrderStatus.CREATED)
                    .totalPrice(BigDecimal.ZERO)
                    .orderItems(List.of())
                    .build();
            Order savedOrder = orderRepository.save(order);
            log.info("Created order {}", savedOrder.getId());
            return orderMapper.toResponseDto(savedOrder);
        } catch (Exception e) {
            log.error("Order could not be created. Error: {}", e.getMessage());
            throw new OrderNotFoundException();
        }
    }

    @Transactional
    public void cancelOrder(UUID orderId) {
        try {
            Order order = findOrderById(orderId);
            order.setStatus(OrderStatus.CANCELLED);
            orderRepository.save(order);
            log.info("Deleted order {}", orderId);
        } catch (Exception e) {
            log.error("Order could not be deleted. Error: {}", e.getMessage());
        }
    }

    @Transactional
    public OrderItemResponseDto addOrderItemToOrder(OrderItemRequestDto orderItemRequestDto) {
        OrderItem orderItem = orderItemMapper.toEntity(orderItemRequestDto);

        boolean isStockSufficient = inventoryClient.isInventorySufficient(orderItem.getProductId(), orderItem.getQuantity());

        if (!isStockSufficient) {
            logger.error("Stock is not sufficient for product: {}", orderItem.getProductId());
            throw new InsufficientStockException(orderItem.getProductId());
        }

        orderItemRepository.save(orderItem);
        logger.info("Order item added successfully: {}", orderItem);

        return orderItemMapper.toResponseDto(orderItem);
    }

    @Transactional
    @Modifying
    public void deleteAllOrderItemsInOrder(UUID orderId) {
        orderItemRepository.deleteByOrderId(orderId);
    }

    @Transactional
    @Modifying
    public OrderResponseDto deleteOrderItem(UUID orderItemId) {
        OrderItem orderItem = orderItemRepository.findById(orderItemId).orElseThrow(OrderItemNotFoundException::new);
        UUID orderId = orderItem.getOrder().getId();

        orderItemRepository.delete(orderItem);
        orderRepository.flush();

        return orderMapper.toResponseDto(findOrderById(orderId));
    }

    @Transactional
    public OrderResponseDto updateOrderItemQuantity(OrderItemUpdateDto orderItemUpdateDto) {
        OrderItem orderItem = orderItemRepository.findById(orderItemUpdateDto.orderItemId()).orElseThrow(OrderItemNotFoundException::new);

        orderItem.setQuantity(orderItemUpdateDto.quantity());
        orderItemRepository.save(orderItem);

        return orderMapper.toResponseDto(orderItem.getOrder());
    }
}