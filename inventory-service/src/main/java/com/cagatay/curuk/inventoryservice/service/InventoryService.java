package com.cagatay.curuk.inventoryservice.service;

import com.cagatay.curuk.inventoryservice.dto.InventoryRequestDto;
import com.cagatay.curuk.inventoryservice.dto.InventoryResponseDto;
import com.cagatay.curuk.inventoryservice.exception.InsufficientStockException;
import com.cagatay.curuk.inventoryservice.exception.InventoryCouldNotCreateException;
import com.cagatay.curuk.inventoryservice.exception.ProductNotFoundException;
import com.cagatay.curuk.inventoryservice.mapper.InventoryMapper;
import com.cagatay.curuk.inventoryservice.model.Inventory;
import com.cagatay.curuk.inventoryservice.repository.InventoryRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class InventoryService {

    private final InventoryRepository inventoryRepository;
    private final InventoryMapper inventoryMapper;

    public List<InventoryResponseDto> getAllInventory() {
        return inventoryRepository.findAll().stream()
                .map(inventoryMapper::toResponseDto)
                .toList();
    }

    public InventoryResponseDto getInventory(UUID productId) {
        Inventory inventory = inventoryRepository.findByProductId(productId)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + productId));

        return inventoryMapper.toResponseDto(inventory);
    }

    public InventoryResponseDto createInventory(InventoryRequestDto inventoryRequestDto) {
        try {
            Inventory inventory = Inventory.builder()
                    .productId(inventoryRequestDto.productId())
                    .quantity(inventoryRequestDto.quantity())
                    .build();

            inventoryRepository.save(inventory);
            log.info("Created inventory for product {}", inventory.getProductId());

            return inventoryMapper.toResponseDto(inventory);
        } catch (Exception e) {
            log.error("Inventory could not be created. Error: {}", e.getMessage());
            throw new InventoryCouldNotCreateException("Inventory could not be created.");
        }
    }

    @Transactional
    public void reduceStock(UUID productId, int quantity) {
        Inventory inventory = inventoryRepository.findByProductId(productId)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + productId));

        if (inventory.getQuantity() < quantity) {
            throw new InsufficientStockException("Insufficient stock for product: " + productId);
        }

        inventory.setQuantity(inventory.getQuantity() - quantity);
        inventoryRepository.save(inventory);
    }

    public void updateStock(UUID productId, int quantityChange) {
        if (quantityChange == 0) return;

        Inventory inventory = inventoryRepository.findByProductId(productId)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + productId));

        int newQuantity = inventory.getQuantity() + quantityChange;

        if (newQuantity < 0)
            throw new InsufficientStockException("Insufficient stock for product: " + productId);

        inventory.setQuantity(newQuantity);

        log.info("Stock updated for product {}", productId);

        inventoryRepository.save(inventory);
    }





    public boolean isInventorySufficient(UUID productId, int quantity) {
        Inventory inventory = inventoryRepository.findByProductId(productId)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + productId));

        return inventory.getQuantity() >= quantity && inventory.getQuantity() > 0;
    }
}