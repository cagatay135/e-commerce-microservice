package com.cagatay.curuk.orderservice.dto;

import lombok.Builder;

import java.io.Serializable;
import java.util.UUID;

@Builder
public record OrderItemUpdateDto(
        UUID orderItemId,
        Integer quantity
) implements Serializable {}