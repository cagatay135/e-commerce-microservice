package com.cagatay.curuk.inventoryservice.dto;

import lombok.Builder;

import java.util.UUID;

@Builder
public record InventoryResponseDto(
        UUID id,
        UUID productId,
        Integer quantity)
{}