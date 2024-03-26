package com.cagataycuruk.productservice.mapper;

import com.cagataycuruk.productservice.dto.ProductRequestDto;
import com.cagataycuruk.productservice.dto.ProductResponseDto;
import com.cagataycuruk.productservice.model.Product;
import org.mapstruct.Mapper;

@Mapper(implementationName = "ProductMapperImpl", componentModel = "spring")
public interface ProductMapper {
    ProductResponseDto toProductResponseDto(Product product);
    Product toProduct(ProductRequestDto productRequestDto, String url);
}