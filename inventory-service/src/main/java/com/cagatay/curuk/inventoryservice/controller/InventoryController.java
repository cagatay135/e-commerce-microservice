package com.cagatay.curuk.inventoryservice.controller;

import com.cagatay.curuk.inventoryservice.dto.InventoryRequestDto;
import com.cagatay.curuk.inventoryservice.dto.InventoryResponseDto;
import com.cagatay.curuk.inventoryservice.service.InventoryService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static com.cagatay.curuk.inventoryservice.constants.InventoryConstants.*;

@Slf4j
@RestController
@RequestMapping(API_INVENTORY)
@AllArgsConstructor
public class InventoryController {

    private InventoryService inventoryService;

    @Operation(summary = "Get all inventory items")
    @GetMapping("")
    public ResponseEntity<List<InventoryResponseDto>> getAllInventory() {
        List<InventoryResponseDto> inventories = inventoryService.getAllInventory();
        return ResponseEntity.ok(inventories);
    }

    @Operation(summary = "Get inventory by product ID")
    @GetMapping("/{productId}")
    public ResponseEntity<InventoryResponseDto> getStock(@PathVariable UUID productId) {
        InventoryResponseDto inventoryDto = inventoryService.getInventory(productId);
        return ResponseEntity.ok(inventoryDto);
    }

    @Operation(summary = "Create inventory for a product")
    @PostMapping("")
    public ResponseEntity<InventoryResponseDto> createInventory(@RequestBody InventoryRequestDto inventoryRequest) {
        InventoryResponseDto inventoryDto = inventoryService.createInventory(inventoryRequest);
        return ResponseEntity.ok(inventoryDto);
    }

    @Operation(summary = "Update inventory for a product")
    @PutMapping("")
    public ResponseEntity<InventoryResponseDto> updateInventory(@RequestBody InventoryRequestDto inventoryRequest) {
        InventoryResponseDto inventoryDto = inventoryService.updateInventory(inventoryRequest);
        return ResponseEntity.ok(inventoryDto);
    }

    @Operation(summary = "Reduce stock for a product")
    @PostMapping("/reduce")
    public ResponseEntity<Void> reduceStock(@RequestParam("productId") UUID productId, @RequestParam("quantity") int quantity) {
        inventoryService.reduceStock(productId, quantity);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Check if stock is sufficient for a product")
    @GetMapping("/stock-check")
    public ResponseEntity<Boolean> checkStock(@RequestParam("productId") UUID productId, @RequestParam("quantity") int quantity) {
        boolean isSufficient = inventoryService.isInventorySufficient(productId, quantity);
        return ResponseEntity.ok(isSufficient);
    }
}