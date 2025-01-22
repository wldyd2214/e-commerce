package com.hhplus.commerce.spring.presentation.product.mapper;

import com.hhplus.commerce.spring.old.api.product.model.Product;
import com.hhplus.commerce.spring.presentation.product.dto.ProductListResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ProductResponseMapper {

    ProductResponseMapper INSTANCE = Mappers.getMapper(ProductResponseMapper.class);

    ProductListResponse toProductResponse(List<Product> product);
}
