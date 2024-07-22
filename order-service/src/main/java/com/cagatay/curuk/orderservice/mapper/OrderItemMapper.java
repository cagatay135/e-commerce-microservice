package com.cagatay.curuk.orderservice.mapper;

import com.cagatay.curuk.orderservice.dto.OrderItemResponseDto;
import com.cagatay.curuk.orderservice.dto.ProductResponseDto;
import com.cagatay.curuk.orderservice.feign.ProductClient;
import com.cagatay.curuk.orderservice.model.OrderItem;
import com.cagatay.curuk.orderservice.repository.OrderItemRepository;
import com.cagatay.curuk.orderservice.repository.OrderRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class OrderItemMapper {
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;

    private final ProductClient productClient;

    public OrderItemResponseDto toResponseDto(OrderItem orderItem) {
        ProductResponseDto product = productClient.getProductById(orderItem.getProductId());

        return OrderItemResponseDto.builder()
                .id(orderItem.getId())
                .productId(orderItem.getProductId())
                .quantity(orderItem.getQuantity())
                .name(product.name())
                .price(product.price())
                .build();
    }

    public List<OrderItemResponseDto> toResponseDtoList(List<OrderItem> orderItems) {
        return orderItems.stream().map(this::toResponseDto).toList();
    }
}