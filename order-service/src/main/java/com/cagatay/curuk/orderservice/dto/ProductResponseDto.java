package com.cagatay.curuk.orderservice.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

public record ProductResponseDto(
        UUID id,
        String name,
        String image,
        BigDecimal price
) implements Serializable {}