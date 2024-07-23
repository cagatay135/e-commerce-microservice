package com.cagatay.curuk.orderservice.mapper;

import com.cagatay.curuk.orderservice.dto.OrderItemResponseDto;
import com.cagatay.curuk.orderservice.dto.OrderResponseDto;
import com.cagatay.curuk.orderservice.model.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;

@Component
@RequiredArgsConstructor
public class OrderMapper {
    private final OrderItemMapper orderItemMapper;

    public OrderResponseDto toResponseDto(Order order){
        List<OrderItemResponseDto> orderItemResponseDtos = orderItemMapper.toResponseDtoList(order.getOrderItems());

        return OrderResponseDto.builder()
                .id(order.getId())
                .status(order.getStatus())
                .orderItems(orderItemResponseDtos.stream().sorted(Comparator.comparing(OrderItemResponseDto::name)).toList())
                .totalPrice(orderItemResponseDtos.stream().map(OrderItemResponseDto::price).reduce(BigDecimal.ZERO, BigDecimal::add))
                .build();
    }
}