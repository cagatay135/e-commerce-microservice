package com.cagataycuruk.productservice.service;

import com.cagataycuruk.productservice.dto.ProductRequestDto;
import com.cagataycuruk.productservice.dto.ProductResponseDto;
import com.cagataycuruk.productservice.exception.ProductCouldNotCreateException;
import com.cagataycuruk.productservice.exception.ProductNotFoundException;
import com.cagataycuruk.productservice.mapper.ProductMapper;
import com.cagataycuruk.productservice.model.Product;
import com.cagataycuruk.productservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public List<ProductResponseDto> getAllProducts() {
        List<Product> products = productRepository.findAll();
        log.info("Retrieved all products: {}", products.size());
        return products.stream().map(productMapper::toProductResponseDto).toList();
    }

    public ProductResponseDto getProductById(UUID id) {
        try {
            Random rand = new Random();
            Thread.sleep(rand.nextInt(2*1000, 10*1000));
        } catch (InterruptedException ie) {
            Thread.currentThread().interrupt();
        }

        Product product = productRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Product Not Found: {}.", id);
                    return new ProductNotFoundException("Product not found");
                });
        log.info("Retrieved product by id: {}", id);
        return productMapper.toProductResponseDto(product);
    }


    public ProductResponseDto createProduct(ProductRequestDto productRequest) {
        Product product = Product.builder()
                .name(productRequest.getName())
                .price(productRequest.getPrice())
                .build();

        try {
            ProductResponseDto savedProduct = productMapper.toProductResponseDto(productRepository.save(product));
            log.info("Created product {}", savedProduct.getId());
            return savedProduct;
        } catch (Exception e) {
            log.error("An error occurred during product creation while processing files.\n {}", e.getMessage());
            throw new ProductCouldNotCreateException("An error occurred during product creation while processing files.");
        }
    }
}