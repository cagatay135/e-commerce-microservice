package com.cagatay.curuk.orderservice.dto;

import lombok.Builder;

import java.math.BigDecimal;
import java.util.UUID;

@Builder
public record OrderItemResponseDto(
        UUID id,
        UUID productId,
        Integer quantity,
        String name,
        BigDecimal price) {
}