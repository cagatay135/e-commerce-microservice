package com.cagataycuruk.productservice.controller;

import com.cagataycuruk.productservice.dto.ProductRequestDto;
import com.cagataycuruk.productservice.dto.ProductResponseDto;
import com.cagataycuruk.productservice.model.Product;
import com.cagataycuruk.productservice.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @Operation(summary = "Get all products")
    @ApiResponses(value =
    @ApiResponse(
            responseCode = "200",
            description = "All products",
            content = @Content(
                    schema = @Schema(implementation = List.class),
                    mediaType = "application/json")))
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProductResponseDto> getAllProducts() {
        return productService.getAllProducts();
    }


    @Operation(summary = "Get product")
    @ApiResponses(value =
    @ApiResponse(
            responseCode = "200",
            description = "Get product by productId",
            content = @Content(
                    schema = @Schema(implementation = ProductResponseDto.class),
                    mediaType = "application/json")))
    @GetMapping("/{productId}")
    @ResponseStatus(HttpStatus.OK)
    public ProductResponseDto getProductById(@PathVariable UUID productId) {
        return productService.getProductById(productId);
    }

    @Operation(summary = "Create product")
    @ApiResponses(value =
    @ApiResponse(
            responseCode = "201",
            description = "Create product",
            content = @Content(
                    schema = @Schema(implementation = String.class),
                    mediaType = "application/json")))
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ProductResponseDto createProduct(@RequestBody ProductRequestDto productRequestDto) {
        return productService.createProduct(productRequestDto);
    }
}