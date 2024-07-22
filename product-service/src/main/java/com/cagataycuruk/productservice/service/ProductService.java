package com.cagataycuruk.productservice.service;

import com.cagataycuruk.productservice.dto.ProductRequestDto;
import com.cagataycuruk.productservice.dto.ProductResponseDto;
import com.cagataycuruk.productservice.exception.ProductCouldNotCreateException;
import com.cagataycuruk.productservice.exception.ProductNotFoundException;
import com.cagataycuruk.productservice.mapper.ProductMapper;
import com.cagataycuruk.productservice.model.Product;
import com.cagataycuruk.productservice.repository.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public List<ProductResponseDto> getAllProducts() {
        return productRepository.findAll().stream()
                .map(productMapper::toProductResponseDto)
                .toList();
    }

    public ProductResponseDto getProductById(UUID productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> {
                    log.error("Product not found with id {}", productId);
                    return new ProductNotFoundException("Product bulunamadı.");
                });
        return productMapper.toProductResponseDto(product);
    }

    @Transactional
    public ProductResponseDto createProduct(ProductRequestDto productRequestDto) {
        try {
            Product product = productMapper.toProduct(productRequestDto);
            Product savedProduct = productRepository.save(product);
            log.info("Created product {}", savedProduct.getId());
            return productMapper.toProductResponseDto(savedProduct);
        } catch (Exception e) {
            log.error("Product could not be created. Error: {}", e.getMessage());
            throw new ProductCouldNotCreateException("Product oluştururken, dosya işlemlerinde hata gerçekleşti.");
        }
    }
}