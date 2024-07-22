package com.cagataycuruk.productservice.dto;

import lombok.Builder;

import java.io.Serializable;
import java.math.BigDecimal;

@Builder
public record ProductRequestDto(
        String name,
        String description,
        BigDecimal price
) implements Serializable {}