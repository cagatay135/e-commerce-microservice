package com.cagataycuruk.productservice.mapper;

import com.cagataycuruk.productservice.dto.ProductRequestDto;
import com.cagataycuruk.productservice.dto.ProductResponseDto;
import com.cagataycuruk.productservice.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(implementationName = "ProductMapperImpl", componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProductMapper {
    Product toProduct(ProductRequestDto productRequestDto);
    ProductResponseDto toProductResponseDto(Product product);
}