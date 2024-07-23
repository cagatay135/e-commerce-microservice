package com.cagatay.curuk.orderservice.dto;

import java.util.UUID;

public record OrderItemRequestDto(
        UUID productId,
        Integer quantity,
        UUID orderId)
{}