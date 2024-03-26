package com.cagataycuruk.productservice.controller;

import com.cagataycuruk.productservice.dto.ProductRequestDto;
import com.cagataycuruk.productservice.dto.ProductResponseDto;
import com.cagataycuruk.productservice.model.Product;
import com.cagataycuruk.productservice.service.ProductService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@Slf4j
@RequestMapping("products")
@AllArgsConstructor
public class ProductController {
    private final ProductService productService;
    private final List<Product> leakyStorage = new ArrayList<>();

    @GetMapping
    public List<ProductResponseDto> getAllProducts()  {
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public ProductResponseDto getProductById(@PathVariable UUID id) {
        return productService.getProductById(id);
    }

    @PostMapping("")
    public ProductResponseDto createProduct(@RequestBody ProductRequestDto productRequest) {
        return productService.createProduct(productRequest);
    }

    @GetMapping("/leak")
    public String leak() {
        for (int i = 0; i < 10000; i++) {
            leakyStorage.add(new Product(new byte[1024]));
        }

        return "product leaks triggered";
    }

    @GetMapping("/leak-count")
    public Integer leakCount() {
        return leakyStorage.size();
    }
}
