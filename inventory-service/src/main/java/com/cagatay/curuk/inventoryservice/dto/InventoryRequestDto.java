package com.cagatay.curuk.inventoryservice.dto;

import lombok.Builder;

import java.util.UUID;

@Builder
public record InventoryRequestDto(
        UUID productId,
        Integer quantity)
{}