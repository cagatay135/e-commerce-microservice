package com.cagatay.curuk.orderservice.dto;


import com.cagatay.curuk.orderservice.enums.OrderStatus;
import lombok.Builder;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Builder
public record OrderResponseDto(
        UUID id,
        BigDecimal totalPrice,
        OrderStatus status,
        List<OrderItemResponseDto> orderItems
        ) {
}