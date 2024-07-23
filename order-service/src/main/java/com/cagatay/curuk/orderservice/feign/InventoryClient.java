package com.cagatay.curuk.orderservice.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.UUID;

@FeignClient(name = "inventory-service")
public interface InventoryClient {
    @PostMapping("/inventory-service/inventories/reduce")
    void reduceStock(@RequestParam("productId") Long productId, @RequestParam("quantity") int quantity);

    @GetMapping("/inventory-service/inventories/stock-check")
    boolean isInventorySufficient(@RequestParam("productId") UUID productId, @RequestParam("quantity") int quantity);
}