package com.cagataycuruk.productservice.dto;

import lombok.Builder;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

@Builder
public record ProductResponseDto(
        UUID id,
        String name,
        String description,
        BigDecimal price
) implements Serializable {}