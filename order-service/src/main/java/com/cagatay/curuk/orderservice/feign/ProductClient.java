package com.cagatay.curuk.orderservice.feign;

import com.cagatay.curuk.orderservice.dto.ProductResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@FeignClient(name = "ProductClient", url = "http://localhost:8081")
public interface ProductClient {
    @GetMapping("/product-service/api/products/{productId}")
    ProductResponseDto getProductById(@PathVariable("productId") UUID productId);
}