package com.cagatay.curuk.inventoryservice.mapper;

import com.cagatay.curuk.inventoryservice.dto.InventoryResponseDto;
import com.cagatay.curuk.inventoryservice.model.Inventory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class InventoryMapper {
    public InventoryResponseDto toResponseDto(Inventory inventory) {
        return InventoryResponseDto.builder()
                .id(inventory.getId())
                .productId(inventory.getProductId())
                .quantity(inventory.getQuantity())
                .build();
    }

    public Inventory toEntity(InventoryResponseDto inventoryResponseDto) {
        return Inventory.builder()
                .productId(inventoryResponseDto.productId())
                .quantity(inventoryResponseDto.quantity())
                .build();
    }
}