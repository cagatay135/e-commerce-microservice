package com.cagatay.curuk.inventoryservice.controller;

import com.cagatay.curuk.inventoryservice.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/inventories")
@RequiredArgsConstructor
public class InventoryController {

    private InventoryService inventoryService;

    @PostMapping("/reduce")
    public ResponseEntity<Void> reduceStock(@RequestParam("productId") UUID productId, @RequestParam("quantity") int quantity) {
        try {
            inventoryService.reduceStock(productId, quantity);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}