package com.cagatay.curuk.orderservice.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "inventory-service")
public interface InventoryClient {
    @PostMapping("/api/inventories/reduce")
    void reduceStock(@RequestParam("productId") Long productId, @RequestParam("quantity") int quantity);
}