package com.cagatay.curuk.inventoryservice.service;

import com.cagatay.curuk.inventoryservice.model.Inventory;
import com.cagatay.curuk.inventoryservice.repository.InventoryRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    public void createInventory(Inventory inventory) {
        try {
            inventoryRepository.save(inventory);
            log.info("Created inventory for product {}", inventory.getProductId());
        } catch (Exception e) {
            log.error("Inventory could not be created. Error: {}", e.getMessage());
        }
    }

    @Transactional
    public void reduceStock(UUID productId, int quantity) {
        Inventory inventory = inventoryRepository.findByProductId(productId)
                .orElseThrow(() -> new RuntimeException("Product not found: " + productId));

        if (inventory.getQuantity() < quantity) {
            throw new RuntimeException("Insufficient stock for product: " + productId);
        }

        inventory.setQuantity(inventory.getQuantity() - quantity);
        inventoryRepository.save(inventory);
    }
}